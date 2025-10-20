/**
 * Admin CRUD Operations Handler
 * Manages Create, Update, Delete operations for admin panel
 */

class AdminCRUD {
    constructor() {
        this.form = null;
        this.actionInput = null;
        this.idInput = null;
        this.passwordInput = null;
        this.createBtn = null;
        this.updateBtn = null;
        this.init();
    }

    init() {
        if (document.readyState === 'loading') {
            document.addEventListener('DOMContentLoaded', () => this.setup());
        } else {
            this.setup();
        }
    }

    setup() {
        // Get form elements
        this.form = document.getElementById('userForm');
        if (!this.form) return;

        this.actionInput = document.getElementById('action');
        this.idInput = document.getElementById('id');
        this.passwordInput = document.getElementById('password');
        this.createBtn = document.getElementById('createBtn');
        this.updateBtn = document.getElementById('updateBtn');

        // Setup event listeners
        this.setupEventListeners();
        
        // Initialize form state
        this.resetForm();
    }

    setupEventListeners() {
        // Create button
        if (this.createBtn) {
            this.createBtn.addEventListener('click', (e) => {
                e.preventDefault();
                this.handleCreate();
            });
        }

        // Update button
        if (this.updateBtn) {
            this.updateBtn.addEventListener('click', (e) => {
                e.preventDefault();
                this.handleUpdate();
            });
        }

        // Reset button
        const resetBtn = document.getElementById('resetBtn');
        if (resetBtn) {
            resetBtn.addEventListener('click', (e) => {
                e.preventDefault();
                this.resetForm();
            });
        }

        // Edit buttons on table rows
        document.querySelectorAll('.btn-edit').forEach(btn => {
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                const userId = btn.getAttribute('data-id');
                this.loadUserToForm(userId);
            });
        });

        // Delete buttons
        document.querySelectorAll('.btn-delete').forEach(btn => {
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                const userId = btn.getAttribute('data-id');
                this.confirmDelete(userId);
            });
        });

        // Toggle status buttons
        document.querySelectorAll('.btn-toggle-status').forEach(btn => {
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                const userId = btn.getAttribute('data-id');
                const isActive = btn.getAttribute('data-active') === 'true';
                this.confirmToggleStatus(userId, isActive);
            });
        });
    }

    handleCreate() {
        if (!this.validateForm()) {
            return;
        }

        this.actionInput.value = 'CREATE';
        this.form.submit();
    }

    handleUpdate() {
        if (!this.validateForm(true)) {
            return;
        }

        this.actionInput.value = 'UPDATE';
        this.form.submit();
    }

    validateForm(isUpdate = false) {
        const id = this.idInput.value.trim();
        const password = this.passwordInput.value.trim();
        const fullname = document.getElementById('fullname').value.trim();
        const email = document.getElementById('email').value.trim();
        const role = document.getElementById('role').value;

        // Clear previous errors
        this.clearErrors();

        let hasError = false;

        if (!id) {
            this.showError('id', 'Tên đăng nhập không được để trống');
            hasError = true;
        }

        if (!isUpdate && !password) {
            this.showError('password', 'Mật khẩu không được để trống');
            hasError = true;
        }

        if (!fullname) {
            this.showError('fullname', 'Họ tên không được để trống');
            hasError = true;
        }

        if (!email) {
            this.showError('email', 'Email không được để trống');
            hasError = true;
        } else if (!this.isValidEmail(email)) {
            this.showError('email', 'Email không hợp lệ');
            hasError = true;
        }

        if (!role) {
            this.showError('role', 'Vui lòng chọn vai trò');
            hasError = true;
        }

        return !hasError;
    }

    isValidEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    showError(fieldId, message) {
        const field = document.getElementById(fieldId);
        if (!field) return;

        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.textContent = message;
        
        field.classList.add('error');
        field.parentNode.appendChild(errorDiv);
    }

    clearErrors() {
        document.querySelectorAll('.error-message').forEach(el => el.remove());
        document.querySelectorAll('.error').forEach(el => el.classList.remove('error'));
    }

    loadUserToForm(userId) {
        // Find user row in table
        const row = document.querySelector(`tr[data-user-id="${userId}"]`);
        if (!row) return;

        // Extract data from row
        const id = row.getAttribute('data-user-id');
        const fullname = row.getAttribute('data-fullname');
        const email = row.getAttribute('data-email');
        const role = row.getAttribute('data-role');

        // Populate form
        this.idInput.value = id;
        this.idInput.readOnly = true; // Can't change ID
        document.getElementById('fullname').value = fullname;
        document.getElementById('email').value = email;
        document.getElementById('role').value = role;
        this.passwordInput.value = ''; // Clear password
        this.passwordInput.placeholder = 'Để trống nếu không đổi mật khẩu';

        // Switch to update mode
        this.switchToUpdateMode();

        // Scroll to form
        this.form.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }

    switchToUpdateMode() {
        this.createBtn.disabled = true;
        this.createBtn.classList.add('disabled');
        this.updateBtn.disabled = false;
        this.updateBtn.classList.remove('disabled');
        this.passwordInput.required = false;
    }

    resetForm() {
        this.form.reset();
        this.idInput.readOnly = false;
        this.passwordInput.placeholder = 'Nhập mật khẩu';
        this.passwordInput.required = true;
        this.createBtn.disabled = false;
        this.createBtn.classList.remove('disabled');
        this.updateBtn.disabled = true;
        this.updateBtn.classList.add('disabled');
        this.clearErrors();
    }

    confirmDelete(userId) {
        if (!confirm(`Bạn có chắc chắn muốn xóa người dùng "${userId}"?\n\nHành động này không thể hoàn tác!`)) {
            return;
        }

        // Create and submit delete form
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = this.getContextPath() + '/admin';

        const actionInput = document.createElement('input');
        actionInput.type = 'hidden';
        actionInput.name = 'action';
        actionInput.value = 'DELETE';

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'id';
        idInput.value = userId;

        form.appendChild(actionInput);
        form.appendChild(idInput);
        document.body.appendChild(form);
        form.submit();
    }

    confirmToggleStatus(userId, isCurrentlyActive) {
        const action = isCurrentlyActive ? 'khóa' : 'mở khóa';
        if (!confirm(`Bạn có chắc chắn muốn ${action} người dùng "${userId}"?`)) {
            return;
        }

        // Create and submit toggle form
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = this.getContextPath() + '/admin';

        const actionInput = document.createElement('input');
        actionInput.type = 'hidden';
        actionInput.name = 'action';
        actionInput.value = 'TOGGLE_STATUS';

        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'id';
        idInput.value = userId;

        form.appendChild(actionInput);
        form.appendChild(idInput);
        document.body.appendChild(form);
        form.submit();
    }

    getContextPath() {
        const meta = document.querySelector('meta[name="context-path"]');
        if (meta) {
            return meta.getAttribute('content');
        }
        const path = window.location.pathname;
        const parts = path.split('/');
        return parts.length > 1 ? '/' + parts[1] : '';
    }
}

// Initialize when script loads
const adminCRUD = new AdminCRUD();

// Auto-hide success/error messages after 5 seconds
document.addEventListener('DOMContentLoaded', () => {
    const alerts = document.querySelectorAll('.alert-success, .alert-error');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 300);
        }, 5000);
    });
});
