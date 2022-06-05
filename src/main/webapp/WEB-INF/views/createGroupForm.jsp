<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>그룹 생성</title>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
    </head>

    <body>

        <header>
            <h2>그룹 생성</h2>
        </header>
        <form action="/group/new" method="post">
            <input id="groupName" type="text" name="groupName" placeholder="그룹명 입력">
            <label for="groupName">그룹명 입력</label>
            열람날짜<input type = "datetime-local" min = "2022-05-30" max = "2025-05-30" id = "openDate" name="openDate" step = "1">

            <input type="password" name="password">
            <input type="submit" id="create" value="그룹생성">
        </form>

        <script>
            var nickname = sessionStorage.getItem('nick');
            window.onload = function() {

                var login_state = sessionStorage.getItem('state');

                if(login_state != '1'){
                    alert("로그인 상태가 아닙니다.");
                    location.href = "Main";
                }
            }

        </script>
    </body>
</html>