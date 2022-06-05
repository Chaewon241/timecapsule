<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
</head>

<body>
<header>
    <h1>로그인</h1>
</header>

<script>
    const id = document.getElementById('email')
    const password = document.getElementById('password')
    const login = document.getElementById('login')
    let errStack = 0;

    function saveSession(){
        sessionStorage.setItem("state", '1');
    }

</script>

<form action="/signIn" method="post">
    <div class="input-box">
        <input id="email" type="email" name="email" placeholder="이메일">
        <label for="email"> 이메일</label>
    </div>

    <div class="input-box">
        <input id="password" type="password" name="password" placeholder="비밀번호">
        <label for="password">비밀번호</label>
    </div>

    <input type="submit" id="login" onclick="saveSession()" value="로그인">

</form>
</body>
</html>
