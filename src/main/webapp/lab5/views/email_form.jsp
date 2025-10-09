<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab 5 - Bài 2</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f6fa;
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
        }

        .card {
            background: #fff;
            padding: 24px 28px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, .08);
            width: 500px; /* Tăng chiều rộng một chút cho phù hợp */
        }

        h2 {
            margin: 0 0 20px;
            text-align: center;
        }

        .form-row {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px;
        }

        label {
            margin-bottom: 6px;
            color: #555;
            font-weight: bold;
        }

        input, textarea {
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            box-sizing: border-box;
            width: 100%;
        }

        textarea {
            resize: vertical;
            min-height: 120px;
        }

        button {
            margin-top: 12px;
            padding: 12px 16px;
            background: #0d6efd;
            color: #fff;
            border: 0;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        button:hover {
            background: #0b5ed7;
        }
    </style>
</head>
<body>

<div class="card">
    <h2>Form Gửi Email</h2>

    <form action="/send_mail" method="post">
        <div class="form-row">
            <label for="from">From:</label>
            <input id="from" type="email" name="from" required>
        </div>

        <div class="form-row">
            <label for="to">To:</label>
            <input id="to" type="email" name="to" required>
        </div>

        <div class="form-row">
            <label for="subject">Subject:</label>
            <input id="subject" type="text" name="subject" required>
        </div>

        <div class="form-row">
            <label for="body">Body:</label>
            <textarea id="body" name="body"></textarea>
        </div>

        <button type="submit">Gửi</button>
    </form>
</div>

</body>
</html>