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
<form action="/timeCapsule/Group/{group_id}" method="get">
    <form action="/time_capsule/{time_capsule_id}" method="get">
        제목:${timeCapsule.title}<br>
        그룹명:${timeCapsule.group.groupName}<br>
        그룹리더:${timeCapsule.group.getLeader()}<br>
        그룸멤버<br>
    <c:forEach items="${timeCapsule.group.groupMember}" var="group">

            <p>${group.getNickname()</p>

        </c:forEach><br><br>

        내용 <br>
        ${timeCapsule.text}


        <br><br>

        멀티미디어파일내용<br>
        ${timeCapsule.multipartFiles}


        <br><br>


        타임캡슐 열람날짜:
        <text>${group.opendate}</text>
        <br>
        <br><br>
    </form>
</form>
</body>


</html>
