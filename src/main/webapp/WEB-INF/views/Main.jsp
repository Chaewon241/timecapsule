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

            section {
                padding: 20px;
                color: black;
                display: flex;
                flex-direction: row-reverse;
            }

            section > div {

                margin: auto;
                width: 200px;
                height: 500px;
                padding: 10px;
            }

            ul{
                margin: 0;
                padding: 0;
                list-style-type: none;
                float: right;
            }

            li{
                display: inline;
                margin: 0 20px 0 0 ;
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

    <section id="tripple">
        <div>a</div>
        <div>b</div>
        <div>c</div>
    </section>
    </body>
</html>
