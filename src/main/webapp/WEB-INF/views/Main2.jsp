<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>그룹 목록</title>
</head>

<body>
<header>
    <h1>메인 화면</h1>
</header>

<form>
    <label>${nickname}님 어서오세요!</label>

    <a href="/group">
        <input type="button" value="그룹 조회">
    </a>

    <a href="/group/new" methods="get">
        <input type="button" value="그룹 생성">
    </a>

    <a href="/member/info">
        <input type="button" value="내 정보">
    </a>

    <a href="/group/${group.id}"> <!-- 그룹조회매핑 다시 확인 -->
        <input type="button" value="내 그룹">
   </a>

    <a href="/signOut">
        <input type="button" value="로그아웃">
    </a>

    <a href="/member/{id}/delete" method="post">
        <input type="button" value="회원 탈퇴">
    </a>
</form>
</body>
</html>