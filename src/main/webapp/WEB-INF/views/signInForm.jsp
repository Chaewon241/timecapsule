<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
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
        .input-box{
            position:relative;
            margin:10px 0;
        }
        .input-box > input{
            background:transparent;
            border:none;
            border-bottom: solid 1px #ccc;
            padding:20px 0px 5px 0px;
            font-size:14pt;
            width:100%;
        }
        input::placeholder{
            color:transparent;
        }
        input:placeholder-shown + label{
            color:#aaa;
            font-size:14pt;
            top:15px;

        }
        input:focus + label, label{
            color:#8aa1a1;
            font-size:10pt;
            pointer-events: none;
            position: absolute;
            left:0px;
            top:0px;
            transition: all 0.2s ease ;
            -webkit-transition: all 0.2s ease;
            -moz-transition: all 0.2s ease;
            -o-transition: all 0.2s ease;
        }

        input:focus, input:not(:placeholder-shown){
            border-bottom: solid 1px #8aa1a1;
            outline:none;
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
