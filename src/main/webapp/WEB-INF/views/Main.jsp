<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>메인화면</title>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <style>
            body{
                margin: 0;
                padding: 0;
                font-size: 1.2em;
                font-family: 'Hack';
            }

            .nav{
                height: 70px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                max-width: 1000px;
                margin: auto;
            }

            .nav .logo{
                width: 100px;
            }

            .nav .nav_but a{
                text-decoration: none;
                padding-left: 20px;
                padding-right: 20px;
                color: black;
            }

            img {
                display: block;
                margin-left: auto;
                margin-right: auto;
            }

            .intro{
                display: flex;
                justify-content: center;
                align-items: center;
                max-width: 1200px;
                margin: auto;
            }

            .intro .card {
                flex: 1;
                text-align: center;
                margin: 150px 40px;
            }

            .intro .card i{
                font-size: 70px;
                color: #444;
                margin-bottom: 30px;
            }

            .intro .card h1{
                letter-spacing: 0.2ch;
            }
            .intro .card p {
                font-size: 20px;
            }

        </style>
    </head>

    <body>
    <div class="nav">
        <div class="logo">
            <h1>Timecapsule</h1>
        </div>
        <div class="nav_but">
            <a href="/signIn">로그인</a>
            <a href="/member/new">회원가입</a>
        </div>
    </div>

    <section class="intro">
        <div class="card">
            <i class="one"></i>
            <h1>타임캡슐의 기능 1</h1>
            <p>그룹을 만들어 소중한 추억을 공유할 수 있다.</p>
        </div>

        <div class="card">
            <i class="two"></i>
            <h1>타임캡슐의 기능 2</h1>
            <p>다양한 멀티미디어를 저장할 수 있다.</p>
        </div>

        <div class="card">
            <i class="three"></i>
            <h1>타임캡슐의 기능 3</h1>
            <p>알림 서비스를 통해 열람날짜를 놓치지 않는다.</p>
        </div>
    </section>
    </body>
</html>
