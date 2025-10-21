package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsletterDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsletterDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import com.wangquocthai.java3_jsp_servlet.lab5.Mailer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Newsletter Subscription Servlet - Handles newsletter signup with email confirmation
 */
@WebServlet(name = "NewsletterServlet", urlPatterns = {"/newsletter"})
public class NewsletterServlet extends HttpServlet {
    
    private NewsletterDAO newsletterDAO;
    
    @Override
    public void init() {
        newsletterDAO = new NewsletterDAOImpl();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        // Validate email
        if (email == null || email.trim().isEmpty()) {
            setErrorMessage(request, "Email không được để trống!");
            redirectBack(request, response);
            return;
        }
        
        // Validate email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            setErrorMessage(request, "Email không hợp lệ!");
            redirectBack(request, response);
            return;
        }
        
        try {
            // Check if email already exists
            Newsletter existing = newsletterDAO.findById(email.trim());
            if (existing != null) {
                if (existing.isEnabled()) {
                    setErrorMessage(request, "Email này đã được đăng ký nhận tin!");
                } else {
                    // Reactivate existing subscription
                    existing.setEnabled(true);
                    newsletterDAO.update(existing);
                    sendWelcomeEmail(email.trim());
                    setSuccessMessage(request, "Đã kích hoạt lại đăng ký nhận tin! Vui lòng kiểm tra email.");
                }
                redirectBack(request, response);
                return;
            }
            
            // Create new newsletter subscription
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(email.trim());
            newsletter.setEnabled(true);
            
            int result = newsletterDAO.insert(newsletter);
            
            if (result > 0) {
                // Send welcome email
                sendWelcomeEmail(email.trim());
                setSuccessMessage(request, "Đăng ký nhận tin thành công! Vui lòng kiểm tra email để xác nhận.");
            } else {
                setErrorMessage(request, "Đăng ký thất bại! Vui lòng thử lại.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            setErrorMessage(request, "Có lỗi xảy ra: " + e.getMessage());
        }
        
        redirectBack(request, response);
    }
    
    private void sendWelcomeEmail(String email) {
        try {
            String from = "ichisora167@gmail.com";
            String subject = "Chào mừng bạn đến với ABC News!";
            String body = buildWelcomeEmailBody();
            
            Mailer.send(from, email, subject, body);
        } catch (Exception e) {
            e.printStackTrace();
            // Log error but don't fail the registration
        }
    }
    
    private String buildWelcomeEmailBody() {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px;">
                    <div style="background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%); color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0;">
                        <h1 style="margin: 0; font-size: 28px;">ABC News</h1>
                        <p style="margin: 10px 0 0 0; font-size: 16px;">Trang tin tức Việt Nam</p>
                    </div>
                    
                    <div style="background: white; padding: 30px; border: 1px solid #ddd; border-top: none; border-radius: 0 0 10px 10px;">
                        <h2 style="color: #1e3c72; margin-top: 0;">Chào mừng bạn đến với bảng tin ABC News!</h2>
                        
                        <p>Cảm ơn bạn đã đăng ký nhận bản tin từ ABC News. Từ giờ bạn sẽ nhận được:</p>
                        
                        <ul style="color: #555;">
                            <li>📰 Tin tức nóng hổi nhất trong ngày</li>
                            <li>🔥 Các sự kiện quan trọng</li>
                            <li>💡 Phân tích chuyên sâu</li>
                            <li>🎯 Nội dung được tuyển chọn đặc biệt</li>
                        </ul>
                        
                        <div style="background: #f8f9fa; padding: 20px; border-radius: 8px; margin: 20px 0;">
                            <p style="margin: 0; font-style: italic; color: #666;">
                                "Đây là email từ bảng tin ABC News - nơi cập nhật thông tin tin cậy và chính xác nhất!"
                            </p>
                        </div>
                        
                        <p>Nếu bạn không muốn nhận email này nữa, vui lòng liên hệ với chúng tôi.</p>
                        
                        <div style="text-align: center; margin-top: 30px; padding-top: 20px; border-top: 1px solid #eee;">
                            <p style="color: #888; font-size: 14px;">
                                © 2024 ABC News. Tất cả quyền được bảo lưu.<br>
                                Email này được gửi tự động, vui lòng không trả lời.
                            </p>
                        </div>
                    </div>
                </div>
            </body>
            </html>
            """;
    }
    
    private void setSuccessMessage(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        session.setAttribute("newsletterSuccess", message);
    }
    
    private void setErrorMessage(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        session.setAttribute("newsletterError", message);
    }
    
    private void redirectBack(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(request.getContextPath() + "/reader");
        }
    }
}
