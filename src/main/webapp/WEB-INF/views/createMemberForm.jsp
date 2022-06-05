<%@ page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
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
    function check_pw(){
        var p = document.getElementById('password').value;
        var p_cf = document.getElementById('ckpassword').value;

        if (p != p_cf) {
            document.getElementById('msg').innerHTML = "";

            return false;
        }
        else if (p == p_cf){
            return true;
        }
        if (p_cf=="") {
            document.getElementById('msg').innerHTML = "";
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
        <input id="ckpassword" type="password" name="ckpassword" placeholder="비밀번호 확인" onkeyup="check_pw()">
        <label for="ckpassword">비밀번호 확인</label>
        <label id="msg"></label>
    </div>

    <button type="submit" onclick="return check_pw()" id="join">회원가입</button>

</form>
</body>
</html>
