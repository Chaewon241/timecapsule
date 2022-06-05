<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>내 정보</title>
    </head>

    <body>
        <header>
            <h1>내 정보</h1>
        </header>

        <form>
            <label>${nickname}님의 정보</label>

            이메일: ${email}
            닉네임: ${nickname}
            전화번호: ${phoneNumber}

            <a href="/member/edit">
                <input type="button" value="정보 수정">
            </a>

        </form>
    </body>
</html>