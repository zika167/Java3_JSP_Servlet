/**
 * Authentication Modal Handler
 * Handles login and signup modals with AJAX
 */

class AuthModal {
    constructor() {
        this.overlay = null;
        this.container = null;
        this.loginForm = null;
        this.signupForm = null;
        this.init();
    }

    init() {
        // Wait for DOM to be ready
        if (document.readyState === 'loading') {
            document.addEventListener('DOMContentLoaded', () => this.setup());
        } else {
            this.setup();
        }
    }

    setup() {
        // Get modal elements
        this.overlay = document.getElementById('authModalOverlay');
        this.container = document.getElementById('authModalContainer');
        this.loginForm = document.getElementById('loginForm');
        this.signupForm = document.getElementById('signupForm');

        if (!this.overlay || !this.container) {
            console.warn('Auth modal elements not found');
            return;
        }

        // Setup event listeners
        this.setupEventListeners();
    }

    setupEventListeners() {
        // Login/Signup link clicks
        document.querySelectorAll('[data-auth-action]').forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                const action = link.getAttribute('data-auth-action');
                this.open(action);
            });
        });

        // Close button
        const closeBtn = document.querySelector('.modal-close');
        if (closeBtn) {
            closeBtn.addEventListener('click', () => this.close());
        }

        // Overlay click
        if (this.overlay) {
            this.overlay.addEventListener('click', () => this.close());
        }

        // Tab switching
        document.querySelectorAll('.modal-tab').forEach(tab => {
            tab.addEventListener('click', () => {
                const target = tab.getAttribute('data-tab');
                this.switchTab(target);
            });
        });

        // Form submissions
        if (this.loginForm) {
            this.loginForm.addEventListener('submit', (e) => {
                e.preventDefault();
                this.handleLogin();
            });
        }

        if (this.signupForm) {
            this.signupForm.addEventListener('submit', (e) => {
                e.preventDefault();
                this.handleSignup();
            });
        }

        // ESC key to close
        document.addEventListener('keydown', (e) => {
            if (e.key === 'Escape' && this.container.classList.contains('active')) {
                this.close();
            }
        });
    }

    open(tab = 'login') {
        this.overlay.classList.add('active');
        this.container.classList.add('active');
        document.body.style.overflow = 'hidden';
        this.switchTab(tab);
    }

    close() {
        this.overlay.classList.remove('active');
        this.container.classList.remove('active');
        document.body.style.overflow = '';
        this.clearForms();
        this.clearMessages();
    }

    switchTab(tab) {
        // Update tabs
        document.querySelectorAll('.modal-tab').forEach(t => {
            t.classList.toggle('active', t.getAttribute('data-tab') === tab);
        });

        // Update forms
        document.querySelectorAll('.modal-form').forEach(f => {
            f.classList.toggle('active', f.id === `${tab}Form`);
        });

        this.clearMessages();
    }

    async handleLogin() {
        const formData = new FormData(this.loginForm);
        const submitBtn = this.loginForm.querySelector('button[type="submit"]');
        
        // Disable button and show loading
        submitBtn.disabled = true;
        submitBtn.textContent = 'Đang đăng nhập...';

        try {
            const response = await fetch(this.getContextPath() + '/auth/login', {
                method: 'POST',
                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams(formData)
            });

            const result = await response.json();

            if (result.success) {
                this.showSuccess('loginForm', 'Đăng nhập thành công! Đang tải lại trang...');
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            } else {
                this.showError('loginForm', result.error || 'Đăng nhập thất bại. Vui lòng thử lại.');
                submitBtn.disabled = false;
                submitBtn.textContent = 'Đăng nhập';
            }
        } catch (error) {
            console.error('Login error:', error);
            this.showError('loginForm', 'Có lỗi xảy ra. Vui lòng thử lại sau.');
            submitBtn.disabled = false;
            submitBtn.textContent = 'Đăng nhập';
        }
    }

    async handleSignup() {
        const formData = new FormData(this.signupForm);
        const submitBtn = this.signupForm.querySelector('button[type="submit"]');
        
        // Validate password match
        const password = formData.get('password');
        const confirmPassword = formData.get('confirmPassword');
        
        if (password !== confirmPassword) {
            this.showError('signupForm', 'Mật khẩu xác nhận không khớp!');
            return;
        }

        // Disable button and show loading
        submitBtn.disabled = true;
        submitBtn.textContent = 'Đang đăng ký...';

        try {
            const response = await fetch(this.getContextPath() + '/auth/signup', {
                method: 'POST',
                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams(formData)
            });

            const result = await response.json();

            if (result.success) {
                this.showSuccess('signupForm', 'Đăng ký thành công! Đang chuyển đến trang đăng nhập...');
                setTimeout(() => {
                    this.switchTab('login');
                    this.clearForms();
                }, 1500);
            } else {
                this.showError('signupForm', result.error || 'Đăng ký thất bại. Vui lòng thử lại.');
                submitBtn.disabled = false;
                submitBtn.textContent = 'Đăng ký';
            }
        } catch (error) {
            console.error('Signup error:', error);
            this.showError('signupForm', 'Có lỗi xảy ra. Vui lòng thử lại sau.');
            submitBtn.disabled = false;
            submitBtn.textContent = 'Đăng ký';
        }
    }

    showError(formId, message) {
        const form = document.getElementById(formId);
        const errorDiv = form.querySelector('.error-message');
        if (errorDiv) {
            errorDiv.textContent = message;
            errorDiv.classList.add('active');
        }
    }

    showSuccess(formId, message) {
        const form = document.getElementById(formId);
        const successDiv = form.querySelector('.success-message');
        if (successDiv) {
            successDiv.textContent = message;
            successDiv.classList.add('active');
        }
    }

    clearMessages() {
        document.querySelectorAll('.error-message, .success-message').forEach(msg => {
            msg.classList.remove('active');
        });
    }

    clearForms() {
        if (this.loginForm) this.loginForm.reset();
        if (this.signupForm) this.signupForm.reset();
        
        // Re-enable buttons
        document.querySelectorAll('.modal-form button[type="submit"]').forEach(btn => {
            btn.disabled = false;
            btn.textContent = btn.getAttribute('data-original-text') || btn.textContent;
        });
    }

    getContextPath() {
        // Get context path from a meta tag or calculate it
        const meta = document.querySelector('meta[name="context-path"]');
        if (meta) {
            return meta.getAttribute('content');
        }
        
        // Fallback: calculate from current URL
        const path = window.location.pathname;
        const parts = path.split('/');
        return parts.length > 1 ? '/' + parts[1] : '';
    }
}

// Initialize modal when script loads
const authModal = new AuthModal();
