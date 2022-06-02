<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>그룹 가입</title>
    </head>

    <body>
    <header>
        <h1>그룹 가입</h1>
    </header>
    <form action="/group/new" method="post">
        <input id="groupName" type="text" name="groupName" placeholder="그룹명 입력">
        <label for="groupName">그룹명 입력</label>

        <input type="submit" value="검색">
    </form>
        <ol>
            <li>그룹명 :<a href="">${groupName}</a>, 열람날짜 :<a href="">${openDate}</a></li>
        </ol>
        <input type="submit" value="가입">
    </body>
</html>