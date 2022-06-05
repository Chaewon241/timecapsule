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
        열람날짜<input type = "date" min = "2022-05-30" max = "2025-05-30" name = "openDate" step = "1">
        <input type="submit" value="그룹생성">
    </form>
    </body>
</html>