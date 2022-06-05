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

            #nickname{
                display:flex;
                justify-content: center;
                font-size:12pt;
                color:rgb(164, 164, 164);
                margin:10px 0px;
            }

            a:link { background-image: linear-gradient(rgba(0, 195, 6, 0.2) 100%, transparent 0); background-position: 0 0.85em; background-repeat: repeat-x; background-size: 1px 0.5em; }

        </style>
        <title>그룹 목록</title>
    </head>

    <body>
        <header>
            <h1>메인 화면</h1>
        </header>

        <form>
            <label id="nickname" name="nickname"></label>

            <a href="/group">
                <input type="button" value="그룹 조회">
            </a>

            <a href="/group/new" methods="get">
                <input type="button" value="그룹 생성">
            </a>

            <a href="/member/info">
                <input type="button" value="내 정보">
            </a>

            <a href="/group/${group.id}"> <!-- 그룹조회매핑 다시 확인 -->
                <input type="button" value="내 그룹">
           </a>

            <a href="/signOut">
                <input type="button" id="logout" value="로그아웃">
            </a>

            <a href="/member/{id}/delete" id="cancel" method="post">
                <input type="button" value="회원 탈퇴">
            </a>
        </form>

        <script>
            window.onload = function() {
                const nick = '${nickname}';
                console.log(nick);

                if(nick != ''){
                    sessionStorage.setItem("nick", nick);
                }

                var data = sessionStorage.getItem('nick');
                document.getElementById('nickname').innerHTML = data + "님 환영합니다.";

                var login_state = sessionStorage.getItem('state');

                if(login_state != '1'){
                    location.href = "Main";
                }
            }

            const btn = document.getElementById('logout');

            btn.addEventListener('click', function (){
                sessionStorage.removeItem("nick");
                sessionStorage.setItem("state", '0');
            })

            const cbtn = document.getElementById('cancel');

            cbtn.addEventListener('click', function (){
                sessionStorage.removeItem("nick");
                sessionStorage.setItem("state", '0');
            })

        </script>
    </body>
</html>