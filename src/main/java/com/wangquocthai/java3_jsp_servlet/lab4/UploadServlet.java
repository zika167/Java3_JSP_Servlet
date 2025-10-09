package com.wangquocthai.java3_jsp_servlet.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@MultipartConfig
@WebServlet(name = "UploadServlet", value = "/upload")
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "/static/files/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lab4/views/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photo = req.getPart("photo");
        if (photo == null || photo.getSize() == 0) {
            req.setAttribute("message", "Vui lòng chọn file ảnh");
            req.getRequestDispatcher("/lab4/views/upload.jsp").forward(req, resp);
            return;
        }

        String fileName = photo.getSubmittedFileName();
        String relativePath = UPLOAD_DIR + fileName;
        String absolutePath = getServletContext().getRealPath(relativePath);

        File target = new File(absolutePath);
        File parent = target.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }

        try (InputStream inputStream = photo.getInputStream()) {
            Path targetPath = Path.of(absolutePath);
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        req.setAttribute("message", "Upload thành công: " + relativePath);
        req.setAttribute("imagePath", req.getContextPath() + relativePath);
        req.getRequestDispatcher("/lab4/views/upload.jsp").forward(req, resp);
    }
}


