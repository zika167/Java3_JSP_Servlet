package com.wangquocthai.java3_jsp_servlet.lab5;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Mailer {
    public static void send(String from, String to, String subject, String body) {
        // Thay thế bằng thông tin tài khoản Gmail của bạn
        final String username = "ichisora167@gmail.com";
        final String password = "xjgk gedp lvsm bvnr";

        // Cấu hình thuộc tính cho SMTP Server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo một phiên làm việc (Session) với Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo một đối tượng MimeMessage
            MimeMessage mail = new MimeMessage(session);

            // Thiết lập thông tin người gửi, người nhận, tiêu đề và nội dung
            mail.setFrom(new InternetAddress(from));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mail.setSubject(subject, "utf-8");
            mail.setText(body, "utf-8", "html");

            // Gửi email
            Transport.send(mail);

        } catch (MessagingException e) {
            // In lỗi ra console nếu có sự cố
            e.printStackTrace();
        }
    }
}
