<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8" name="viewport"
          content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <style>
        header, footer {
            display: flex;
            justify-content: center;
        }

        form {
            margin-top: 100px;
            margin-left: 300px;
            margin-right: 300px;
        }

        .input-box {
            position: relative;
            margin: 10px 0;
        }

        .input-box > input {
            background: transparent;
            border: none;
            border-bottom: solid 1px #ccc;
            padding: 20px 0px 5px 0px;
            font-size: 14pt;
            width: 100%;
        }

        button {
            background-color: #8aa1a1;
            border: none;
            color: white;
            border-radius: 5px;
            width: 100%;
            height: 35px;
            font-size: 14pt;
            margin-top: 20px;
            justify-content: center;
        }

        a:link {
            background-image: linear-gradient(rgba(0, 195, 6, 0.2) 100%, transparent 0);
            background-position: 0 0.85em;
            background-repeat: repeat-x;
            background-size: 1px 0.5em;
        }

    </style>
    <title>내 그룹</title>
</head>

<body>
<header>
    <h1>내 그룹</h1>
</header>
    <form action="/group/${group.id}" method="post">
        <p>그룹ID : ${group.id}, 그룹이름 : ${group.groupName}</p>
        <p>그룹리더 : ${group.getLeader()}</p>
        <label for="openDate">타임캡슐 열람날짜 : </label>
        <input type="datetime-local" value="${group.openDate}" id="openDate" name="openDate"
               min = "2022-05-30" max = "2025-05-30" step="1">
        <button type="submit">열람날짜 수정</button>
    </form>
</form>
<footer>
    <a href="/main2">홈으로</a>
</footer>
</body>
</html>