<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<html>
    <head>
        <title>Lab4 - Đăng ký</title>
        <style>
            body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; }
            .card { background: #fff; padding: 24px 28px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 680px; }
            h1 { margin: 0 0 16px; font-size: 32px; }
            .row { display: flex; align-items: center; margin-bottom: 12px; gap: 12px; }
            .row label { width: 160px; font-weight: 600; }
            .row input[type=text], .row input[type=password], .row select, .row textarea {
                flex: 1; padding: 10px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px;
            }
            .row .inline { display: inline-flex; align-items: center; gap: 8px; margin-right: 16px; }
            textarea { height: 120px; }
            .actions { padding-top: 8px; border-top: 1px solid #e5e7eb; margin-top: 12px; }
            button { padding: 10px 16px; background: #0d6efd; color: #fff; border: 0; border-radius: 6px; cursor: pointer; }
            .msg { color: #0d6efd; margin-bottom: 12px; }
        </style>
    </head>
    <body>
        <div class="card">
            <h1>Đăng Ký</h1>
            <p class="msg">${message}</p>
            <form method="post" action="/account/register">
                <div class="row">
                    <label for="username">Tên đăng nhập:</label>
                    <input id="username" name="username" type="text" value="${param.username}"/>
                </div>
                <div class="row">
                    <label for="password">Mật khẩu:</label>
                    <input id="password" name="password" type="password"/>
                </div>
                <div class="row">
                    <label>Giới tính:</label>
                    <span class="inline">
                        <input type="radio" name="gender" value="Male" ${param.gender == 'Male' ? 'checked' : ''}/>
                        Nam
                    </span>
                    <span class="inline">
                        <input type="radio" name="gender" value="Female" ${param.gender == 'Female' ? 'checked' : ''}/>
                        Nữ
                    </span>
                </div>
                <div class="row">
                    <label>Đã có gia đình?</label>
                    <span class="inline">
                        <input type="checkbox" name="married" value="true" ${param.married != null ? 'checked' : ''}/>
                    </span>
                </div>
                <div class="row">
                    <label for="country">Quốc tịch:</label>
                    <select id="country" name="country">
                        <option ${param.country == 'United States' ? 'selected' : ''}>United States</option>
                        <option ${param.country == 'Viet Nam' ? 'selected' : ''}>Viet Nam</option>
                        <option ${param.country == 'Japan' ? 'selected' : ''}>Japan</option>
                    </select>
                </div>
                <div class="row">
                    <label>Sở thích:</label>
                    <span class="inline">
                        <input
                                type="checkbox" name="hobbies"
                                value="Đọc sách" ${fn:contains(paramValues.hobbies, 'Đọc sách') ? 'checked' : ''}
                        />
                        Đọc sách
                    </span>
                    <span class="inline">
                        <input
                                type="checkbox" name="hobbies"
                                value="Du lịch" ${fn:contains(paramValues.hobbies, 'Du lịch') ? 'checked' : ''}
                        />
                        Du lịch
                    </span>
                    <span class="inline">
                        <input
                                type="checkbox" name="hobbies"
                                value="Âm nhạc" ${fn:contains(paramValues.hobbies, 'Âm nhạc') ? 'checked' : ''}
                        />
                        Âm nhạc
                    </span>
                    <span class="inline">
                        <input
                                type="checkbox" name="hobbies"
                                value="Khác" ${fn:contains(paramValues.hobbies, 'Khác') ? 'checked' : ''}
                        />
                        Khác
                    </span>
                </div>
                <div class="row">
                    <label for="note">Ghi chú:</label>
                    <textarea id="note" name="note">${param.note}</textarea>
                </div>
                <div class="actions">
                    <button type="submit">Đăng ký</button>
                </div>
            </form>
            <a href="/lab4" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>


