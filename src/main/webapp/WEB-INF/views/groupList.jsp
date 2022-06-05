<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport"
              content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>그룹 목록</title>
    </head>

    <body>
    <header>
        <h1>그룹 목록</h1>
    </header>
    <form action="/groupList" method="post" accept-charset="utf-8">
        <select name="sel" multiplesize="2">
            <option value="groupname">그룹명</option>
            <option value="leadername">리더명</option>
        </select>
        <input id="searchText" type="text" name="searchText" placeholder="검색할 값 입력">
        <button type="submit">검색</button>
    </form>
    <section id="sec">
        <h3>그룹 리스트</h3>
        <c:forEach items="${groups}" var="group">
            <form action="/group/${group.id}" method="get">
                <p>그룹ID : ${group.id}, 그룹이름 : ${group.groupName}</p>
                <p>그룹리더 : ${group.getLeader()}</p>
                <p>타임캡슐 열람날짜 : ${group.openDate}</p>
                <button type="submit">가입하기</button>
            </form>
        </c:forEach>
    </section>
    </body>
</html>