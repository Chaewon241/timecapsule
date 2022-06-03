<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>그룹 목록</title>
    </head>

    <body>
        <script>
            const showValue = (target) => {
                const text = target.options[target.selectedIndex].text;
                document.getElementById('selname').innerHTML;
            }

            window.onload = function(){
                var sec = document.getElementById('sec');
                var p = document.getElementById('p');
                var jbBtn = document.createElement( 'button' );
                var jbBtnText = document.createTextNode( '가입' );

                var arr = ${groups};
                var i;

                for(i = 0; i < arr.length; i++){
                    p.innerHTML = "그룹명: " + ${groups[i].groupName} + " , 리더명: " + ${groups[i].leaderName} +
                        ", 열람날짜: " + ${groups[i].openDate} +"<br>";
                    let f = document.createElement('form');
                    f.setAttribute('method', 'post');
                    f.setAttribute('action', '/group');
                    p.appendChild(jbBtn.appendChild(jbBtnText));
                    jbBtn[i].addEventListener("click", click);
                    sec.appendChild(p);
                }
            }

            function click(){

            }

        </script>
        <header>
            <h1>그룹 목록</h1>
        </header>
        <form action="/groupList" method="get">
            <select name = "sel"  multiplesize = "2" onchange="showValue(this)">
                <option value="groupname">그룹명</option>
                <option value="leadername">리더명</option>
            </select>
            <div id="selname"></div>
            <input id="searchText" type="text" name="searchText" placeholder="검색할 값 입력">
            <label for="searchText">검색할 값 입력</label>
            <input type="submit" id="search" value="검색">
            <section id="sec">
                <p id="p"></p>
            </section>
        </form>
    </body>
</html>