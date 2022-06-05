<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0"> 
        <link rel="stylesheet" href="style.css">
        <title>타임캡슐작성</title>
    </head>
    <body>
    <form action="/timecapsule/new" method="post">
    제목: <input type="text" name = "title">

    <br><br>

        <form action="/group" method ="get">
    그룹: <select name = "groupName" multiplesize = "4" >

        <option value ="groups">그룹명</option>
        </select>
    </form>
        <br><br>

        타입캡슐내용<br>
        <textarea name = "text" cols ="100" rows = "10"
                  placeholder="타임캡슐에 들어갈 내용을 작성해주세요.">

        </textarea>

        <br><br>

        파일추가삽입<br>


        <input type = "file" name = "multipartFiles" id = "multipartFiles"><input type ="submit" value = "파일저장" name="multipartFiles">

        <br><br>



        타입캡슐개봉날짜<br>
        <form action ="/timecapsule/new" method = "post">
        <input type = "date" min = "2022-05-30" max = "2030-05-30" name = "saveDate" step = "1">

        <br><br>
    
    </form>
        <input type="button" name = "savetimecapsule" value  = "저장">
        </form>

    </body>
</html>
