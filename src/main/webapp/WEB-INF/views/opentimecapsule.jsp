<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>타임캡슐개봉</title>
    </head>
    <body>
        ${msg}
        제목: ${timeCapsule.title}<br>
        그룹명: ${timeCapsule.group.groupName}<br>
        그룹리더: ${timeCapsule.group.getLeader()}<br><br>
        내용 <br>
        ${timeCapsule.text}
        <br><br>

        첨부 파일<br>
        <c:forEach items="${timeCapsule.files}" var="file">
            <tr>
                <td><img src="/images/${file.getStoreFileName()}"></td>
            </tr>
        </c:forEach>

        <br><br>
        타임캡슐 열람 가능일:
        <text>${timeCapsule.group.openDate}</text>
        <br>
        <br><br>
    </body>
</html>