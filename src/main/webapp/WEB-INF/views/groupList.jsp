<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>그룹 목록</title>
    </head>

    <body>
        <script>
            var pw = document.getElementById('password');
            var ckpw = document.getElementById('ckpassword');

            window.onload = function(){
                pw.style.display = "none";
                ckpw.style.display = "none";
            }

            function joingroup(){
                if((pw.style.display = 'none') && (ckpw.style.display = 'none')) {
                    pw.style.display = "block";
                    ckpw.style.display = "block";
                }
                else{
                    pw.style.display = "none";
                    ckpw.style.display = "none";
                }
            }

        </script>
        <header>
            <h1>그룹 목록</h1>
        </header>
        <form action="/groupList" method="post">
            <select name = "sel"  multiplesize = "2">
                <option value="groupname">그룹명</option>
                <option value="leadername">리더명</option>
            </select>
            <input id="searchText" type="text" name="searchText" placeholder="검색할 값 입력">
            <label for="searchText">검색할 값 입력</label>
            <input type="submit" id="search" value="검색">

            <c:forEach items="${groups}" var="group">
                <form action="/group/${group.id}/join" method="get">
                    <p>${group.id}</p>
                    <p>${group.groupName}</p>
                    <p>${group.openDate}</p>
                    <input id="password" type="password" name="password" placeholder="비밀번호">
                    <label for="password">비밀번호</label>
                    <button type="submit" id="join" onclick="joingroup">가입하기</button>
                </form>
            </c:forEach>
        </form>

    </body>
</html>