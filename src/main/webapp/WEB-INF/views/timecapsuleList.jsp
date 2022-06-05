<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title> 타임캡슐리스트조회</title>

</head>
<body>
<form action="/groupList" method="get">
    ${groupname}
</form>
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

    .tg .tg-x5du {
        background-color: #ffce93;
        border-color: #efefef;
        text-align: left;
        vertical-align: top
    }

    .tg .tg-ly6r {
        border-color: #efefef;
        text-align: left;
        vertical-align: top
    }
</style>
<table class="tg">
    <thead>
    <tr>
        <th class="tg-x5du">제목</th>
        <th class="tg-x5du">타임캡슐개봉예정일</th>

    </tr>
    </thead>
    <tbody>

    <tr>
        <td class="tg-ly6r"><c:forEach items="${timeCapsules}" var="timeCapsule">
            <form action="/timeCapsule/${timeCapsule.id}" method="get">
                <p> 타임캡슐제목: ${timeCapsule.title} </p>
            </form>
        </c:forEach></td>
        <td class="tg-ly6r"><c:forEach items="${groups}" var="group">
            <form action="/group/${group.id}" method="get">
                <p> 타임캡슐개봉일: ${group.openDate} </p>
            </form>
        </c:forEach></td>

    </tr>
    </tbody>
</table>
</form><br><br>
<button onclick="location.href = 'makenewtimecapsule.jsp'" value="타임캡슐생성"></button><br>
<button onclick="location.href = 'changeopendatetimecapsule.jsp'" value="타임캡슐개봉일변경"></button><br>


</body>
</html>
