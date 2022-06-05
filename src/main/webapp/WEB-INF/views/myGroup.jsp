<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>내 그룹</title>
</head>

<body>
<script>
    window.onload = function(){
        var sec = document.getElementById('sec');
        var jbBtn = document.createElement( 'button' );
        var jbBtnText = document.createTextNode( '타임캡슐 조회' );
        var arr = ${groups};

        for(var i = 0; i < arr.length; i++){
            var p1 = document.createElement('p');
            var p2 = document.createElement('p');

            p1.innerHTML = "그룹 아이디: " + ${groups[i].id};
            p2.innerHTML = "그룹명: " + ${groups[i].groupName} + " , 리더명: " + ${leaders[i].nickname} +
                ", 열람날짜: " + ${groups[i].openDate} +"<br>";

            let f = document.createElement('form');
            f.setAttribute('method', 'post');
            f.setAttribute('action', '/group'); // 그룹 클릭하면 타임캡슐 목록으로 넘어가는 부분

            p2.appendChild(jbBtn.appendChild(jbBtnText));
            jbBtn[i].addEventListener("click", f.submit());
            sec.appendChild(p1);
            sec.appendChild(p2);
        }
    }

</script>
<header>
    <h1>내 그룹</h1>
</header>
    <section id="sec">

    </section>
</form>
</body>
</html>