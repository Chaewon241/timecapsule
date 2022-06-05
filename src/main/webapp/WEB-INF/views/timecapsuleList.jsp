<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h3>타임캡슐 목록</h3>
<style type="text/css">
    .tg {
        border-collapse: collapse;
        border-spacing: 0;
    }

    .tg td {
        border-color: black;
        border-style: solid;
        border-width: 1px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
    }

    .tg th {
        border-color: black;
        border-style: solid;
        border-width: 1px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
    }

    .tg .tg-90vo {
        background-color: #ffce93;
        border-color: #efefef;
        text-align: center;
        vertical-align: top
    }

    .tg .tg-li6d {
        border-color: #efefef;
        text-align: center;
        vertical-align: top
    }
</style>

<table class="tg">
    <thead>
    <tr>
        <th class="tg-x5du">타임캡슐이름</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${timeCapsules}" var="timeCapsule">
        <tr>
            <td>${timeCapsule.title}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br><br>
<input type="button" value="새로운 타임캡슐 생성" onclick="location.href=/timeCapsule/new'"> <br>
</body>
</html>

