<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <style>
            header{
                display:flex;
                justify-content: center;
            }
            form{
                margin-top: 100px;
                margin-left:300px;
                margin-right:300px;
            }

            #nickname{
                display:flex;
                justify-content: center;
                font-size:12pt;
                color:rgb(164, 164, 164);
                margin:10px 0px;
            }

            input[type=submit]{
                background-color: #8aa1a1;
                border:none;
                color:white;
                border-radius: 5px;
                width:100%;
                height:35px;
                font-size: 14pt;
                margin-top:20px;
                justify-content: center;
            }

            input[type=button]{
                background-color: #8aa1a1;
                border:none;
                color:white;
                border-radius: 5px;
                width:100%;
                height:35px;
                font-size: 14pt;
                margin-top:20px;
                justify-content: center;
            }

            #verify{
                background-color: #8aa1a1;
                border:none;
                color:white;
                border-radius: 5px;
                width:40px;
                height:20px;
                font-size: 10pt;
                margin-top:10px;
            }

            #forgot{
                text-align: right;
                font-size:12pt;
                color:rgb(164, 164, 164);
                margin:10px 0px;
            }

            a:link { background-image: linear-gradient(rgba(0, 195, 6, 0.2) 100%, transparent 0); background-position: 0 0.85em; background-repeat: repeat-x; background-size: 1px 0.5em; }

        </style>
        <title>내 정보</title>
    </head>

    <body>
        <header>
            <h1>내 정보</h1>
        </header>

        <form>
            <label id="nickname">${nickname}님의 정보</label>

            <p>이메일: ${email}</p>
            <p>닉네임: ${nickname}</p>
            <p>전화번호: ${phoneNumber}</p>

            <a href="/member/edit">
                <input type="button" value="정보 수정">
            </a>

        </form>
    </body>
</html>