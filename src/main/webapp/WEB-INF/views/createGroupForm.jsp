<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>그룹 생성</title>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <style>
            header, footer{
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

            a:link { background-image: linear-gradient(rgba(0, 195, 6, 0.2) 100%, transparent 0); background-position: 0 0.85em; background-repeat: repeat-x; background-size: 1px 0.5em; }

        </style>
    </head>

    <body>
        <header>
            <h2>그룹 생성</h2>
        </header>
        <form action="/group/new" method="post">
            <div class="input-box">
                <input id="groupName" type="text" name="groupName" placeholder="그룹명 입력">
                <label for="groupName">그룹명 입력</label>
            </div>
            <div class="input-box">
                열람날짜
                <input type = "datetime-local" min = "2022-05-30" max = "2025-05-30" id = "openDate" name="openDate" step = "1">
            </div>

            <div class="input-box">
                <input id="password" type="password" name="password" placeholder="비밀번호">
                <label for="password">비밀번호</label>
            </div>
            <input type="submit" id="create" onclick="return formcheck()" value="그룹생성">
        </form>
        <footer>
            <a href="/main2">홈으로</a>
        </footer>
        <script>
            function formcheck(){
                var groupName = document.getElementById('groupName');
                var openDate = document.getElementById('openDate');
                var p = document.getElementById('password');

                if(groupName.value == ""){
                    alert("그룹명을 입력하세요.");
                    groupName.focus();
                    return false;
                }
                if(openDate.value == ""){
                    alert("열람날짜를 입력하세요.");
                    openDate.focus();
                    return false;
                }
                if(p.value == ""){
                    alert("비밀번호를 입력하세요.");
                    p.focus();
                    return false;
                }
            }
        </script>
    </body>
</html>