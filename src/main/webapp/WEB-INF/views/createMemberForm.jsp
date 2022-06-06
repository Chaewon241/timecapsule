<%@ page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
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

        button{
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
        a:link { background-image: linear-gradient(rgba(0, 195, 6, 0.2) 100%, transparent 0); background-position: 0 0.85em; background-repeat: repeat-x; background-size: 1px 0.5em; }

    </style>
</head>

<body>
<header>
    <h2>회원가입</h2>
</header>
<script>
    <%!
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888)+1111);
    %>

    var authKey;

    async function post(path, body, headers = {}){
        const url = path;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                ...headers,
            },
            body: JSON.stringify(body),
        };
        const res = await fetch(url, options);
        const data = await res.json();
        if(res.ok){
            return data;
        } else {
            throw Error(data);
        }
    }

    function runAuth(){
        const temp = document.getElementById("phoneNumber").value;
        const tempKey = authKey;

        post("/member/phone", {
            phoneNumber: temp,
            authKey: tempKey,
        })
            .then((data) => {
            })
            .catch((error) => {
                console.log(error);
            });
    }

    function makeAuth(){
        authKey = <%=authKey%>
       runAuth();
    }

    function check(){
        const key = document.getElementById("key").value;
        if(key == authKey){
            alert("인증되었습니다.");
        }
    }

    <!-- 비밀번호랑 비밀번호 확인 같은지 -->
    function formcheck(){
        var email = document.getElementById('email');
        var nickname = document.getElementById('nickname');
        var phoneNumber = document.getElementById('phoneNumber');
        var p = document.getElementById('password');
        var p_cf = document.getElementById('ckpassword');

        if(email.value == ""){
            alert("이메일을 입력하세요.");
            email.focus();
            return false;
        }
        if(nickname.value == ""){
            alert("닉네임을 입력하세요.");
            nickname.focus();
            return false;
        }
        if(phoneNumber.value == ""){
            alert("핸드폰 번호를 입력하세요.");
            phoneNumber.focus();
            return false;
        }
        if(p.value == ""){
            alert("비밀번호를 입력하세요.");
            p.focus();
            return false;
        }
        if(p_cf.value == ""){
            alert("비밀번호확인을 입력하세요.");
            p_cf.focus();
            return false;
        }

        if (p.value !=p_cf.value) {
            document.getElementById('msg').innerHTML = "비밀번호가 다릅니다. 다시 확인해 주세요.";
            return false;
        }
        else {
            document.getElementById('msg').innerHTML = "비밀번호 확인";
        }
        if (p_cf.value == "") {
            document.getElementById('msg').innerHTML = "비밀번호 확인";
        }
    }

</script>
<form action="/member/new" id="create" method="post">
    <div class="input-box">
        <input id="email" type="email" name="email" placeholder="이메일">
        <label for="email"> 이메일</label>
    </div>

    <div class="input-box">
        <input id="nickname" type="text" name="nickname" placeholder="닉네임">
        <label for="nickname">닉네임</label>
    </div>

    <div class="input-box">
        <input id="phoneNumber" type="text" name="phoneNumber" placeholder="핸드폰번호">
        <label for="phoneNumber">핸드폰번호</label>
        <button type="button" onclick="makeAuth()">인증번호 받기</button>
    </div>

    <div class="input-box">
        <input id="key" type="text" name="key" placeholder="인증번호">
        <label for="key">인증번호</label>
        <button type="button" onclick="check()">인증</button>
    </div>

    <div class="input-box">
        <input id="password" type="password" name="password" placeholder="비밀번호">
        <label for="password">비밀번호</label>
    </div>

    <div class="input-box">
        <input id="ckpassword" type="password" name="ckpassword" placeholder="비밀번호 확인">
        <label for="ckpassword" id="msg">비밀번호 확인</label>
    </div>

    <button id="join" onclick="return formcheck()" type="submit">회원가입</button>

</form>
</body>
</html>
