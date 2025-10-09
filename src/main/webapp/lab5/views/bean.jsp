<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab5 - Bài 1</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; }
        .card { background: #fff; padding: 24px 28px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 420px; }
        h2 { margin: 0 0 16px; }
        .form-row { display: flex; flex-direction: column; margin-bottom: 12px; }
        label { margin-bottom: 6px; color: #555; }
        input, select { padding: 10px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; box-sizing: border-box; width: 100%;}
        button { margin-top: 12px; padding: 10px 16px; background: #0d6efd; color: #fff; border: 0; border-radius: 6px; cursor: pointer; width: 100%; }
        button:hover { background: #0b5ed7; }
        .error-message { color: #dc3545; font-size: 12px; margin-top: 4px; }
        /* Styles for radio and checkbox groups */
        .options-group { flex-direction: row; align-items: center; }
        .options-group > label:first-child { /* The main label e.g. "Gender:" */
            margin-bottom: 0;
            margin-right: 15px;
            flex-shrink: 0;
            width: auto;
        }
        .options-group input[type="radio"],
        .options-group input[type="checkbox"] {
            width: auto;
            margin-right: 5px;
        }
        .options-group label {
            margin-bottom: 0; /* Override default label margin */
            margin-right: 15px;
            font-weight: normal;
            width: auto;
        }
    </style>
</head>
<body>
<div class="card">
    <h2>User Information</h2>
    <c:url value="/save" var="url"/>
    <form action="${url}" method="post">
        <%-- Fullname: Bắt buộc nhập --%>
        <div class="form-row">
            <label for="fullname">Fullname</label>
            <input id="fullname" name="fullname" required minlength="2">
            <%-- Hiển thị lỗi từ Server --%>
            <span class="error-message">${errors.fullname}</span>
        </div>

        <%-- Birthday: Bắt buộc và phải là kiểu date --%>
        <div class="form-row">
            <label for="birthday">Birthday</label>
            <input id="birthday" name="birthday" required type="date">
            <span class="error-message">${errors.birthday}</span>
        </div>

        <%-- Gender: Bắt buộc chọn 1 --%>
        <div class="form-row options-group">
            <label>Gender:</label>
            <input type="radio" name="gender" value="true" id="male" required>
            <label for="male">Male</label>
            <input type="radio" name="gender" value="false" id="female">
            <label for="female">Female</label>
        </div>
        <div class="form-row">
            <span class="error-message">${errors.gender}</span>
        </div>

        <%-- Hobbies: Không bắt buộc --%>
        <div class="form-row options-group">
            <label>Hobbies:</label>
            <input type="checkbox" name="hobbies" value="R" id="reading"><label for="reading">Reading</label>
            <input type="checkbox" name="hobbies" value="T" id="traveling"><label for="traveling">Traveling</label>
            <input type="checkbox" name="hobbies" value="M" id="music"><label for="music">Music</label>
        </div>

        <%-- Country: Bắt buộc chọn --%>
        <div class="form-row">
            <label for="country">Country</label>
            <select id="country" name="country" required>
                <option value="">-- Select a country --</option>
                <option value="VN">Việt Nam</option>
                <option value="US">United States</option>
            </select>
            <span class="error-message">${errors.country}</span>
        </div>

        <%-- Salary: Bắt buộc, là số, và > 0 --%>
        <div class="form-row">
            <label for="salary">Salary</label>
            <input id="salary" name="salary" required type="number" min="1">
            <span class="error-message">${errors.salary}</span>
        </div>
        <button type="submit">Submit</button>
        <a href="/lab5" style="padding-top: 20px; display: block; text-align: center;">Về trang tổng hợp bài tập</a>
    </form>
</div>
</body>
</html>
