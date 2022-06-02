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

    login.addEventListener('click', () => {
        if (id.value == 'acid') {
            if (password.value == '0000') {
                alert('로그인 되었습니다!')
            }
            else {
                alert('아이디와 비밀번호를 다시 한 번 확인해주세요!')
                errStack ++;
            }
        }
        else {
            alert('존재하지 않는 계정입니다.')
        }

        if (errStack >= 5) {
            alert('비밀번호를 5회 이상 틀리셨습니다. 비밀번호 찾기를 권장드립니다.')
        }
    })
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

    <input type="submit" id="login" value="로그인">

</form>
</body>
</html>
