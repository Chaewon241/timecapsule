<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset = "utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>타임캡슐개봉</title>
</head>
<body>
<form action="/Group/{id}" method="get"><form action="/time_capsule/{time_capsule_id}" method="get">
    제목:<text>${title}</text><br>
    그룹명:<text>${groupname}</text><br>
    리더명:<text>${leadername}</text><br>
    
    내용 <br>
    <textarea>${text}</textarea>




    <br><br>

    멀티미디어파일내용<br>
    <source> ${multiPartFiles}</source>





    <br><br>



 
    개봉일자:<text>${Opendate}</text><br>
    <br><br>
</form></form>

</body>
</html>
