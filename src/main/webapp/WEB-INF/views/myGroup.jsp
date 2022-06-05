<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>내 그룹</title>
    </head>

    <body>
        <header>
            <h1>내 그룹</h1>
        </header>

        <c:forEach items="${groups}" var="group">
            <form action="/group/${group.id}/join" method="get"> <!-- 타임캡슐 조회 페이지로 매핑 다시하기 -->
                그룹 아이디: <p>${group.id}</p>
                그룹 이름: <p>${group.groupName}</p>
                타임캡슐 열람날짜: <p>${group.openDate}</p>

                <button type="submit">타임캡슐 조회</button>
            </form>
        </c:forEach>
        </form>
    </body>
</html>