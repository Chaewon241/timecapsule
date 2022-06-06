<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
</head>
<style>
    header {
        display: flex;
        justify-content: center;
    }

    form {
        margin-top: 100px;
        margin-left: 300px;
        margin-right: 300px;
    }

    .input-box {
        position: relative;
        margin: 10px 0;
    }

    .input-box > input {
        background: transparent;
        border: none;
        border-bottom: solid 1px #ccc;
        padding: 20px 0px 5px 0px;
        font-size: 14pt;
        width: 100%;
    }

    input::placeholder {
        color: transparent;
    }

    input:placeholder-shown + label {
        color: #aaa;
        font-size: 14pt;
        top: 15px;

    }

    input:focus + label, label {
        color: #8aa1a1;
        font-size: 10pt;
        pointer-events: none;
        position: absolute;
        left: 0px;
        top: 0px;
        transition: all 0.2s ease;
        -webkit-transition: all 0.2s ease;
        -moz-transition: all 0.2s ease;
        -o-transition: all 0.2s ease;
    }

    input:focus, input:not(:placeholder-shown) {
        border-bottom: solid 1px #8aa1a1;
        outline: none;
    }

    button {
        background-color: #8aa1a1;
        border: none;
        color: white;
        border-radius: 5px;
        width: 100%;
        height: 35px;
        font-size: 14pt;
        margin-top: 20px;
        justify-content: center;
    }

    h3 {
        display: flex;
        justify-content: center;
        margin: 50px 0px;
    }

    hr {
        border: 0;
        height: 3px;
        margin: 50px 0px;
        background: #ccc;
    }
</style>
<body>
<script>
    var opendate = "${findDate}";
    var date = opendate.split(/[-T:]/);
    var o_year = date[0];
    var o_month = date[1];
    var o_date = date[2];
    var o_hours = date[3];
    var o_minutes = date[4];
    var o_seconds = date[5];
    var yyyy = Number(o_date);
    var mm = Number(o_month);
    var dd = Number(o_date);
    var hh = Number(o_hours);
    var mm = Number(o_minutes);
    var ss = Number(o_seconds);
    var all_opendate = yyyy * 31557600 + mm * 2629800 + dd * 86400 + hh * 3600 + mm * 60 + ss;

    function getTime() {
        <%
        LocalDateTime temp = LocalDateTime.now();
        String tempString = temp.toString();
        %>
        var today = "<%=tempString%>";
        var s_year = today[0];
        var s_month = today[1];
        var s_date = today[2];
        var s_hours = today[3];
        var s_minutes = today[4];
        var s_seconds = today[5];
        var year = Number(s_year);
        var month = Number(s_month);
        var date = Number(s_date);
        var hours = Number(s_hours);
        var minutes = Number(s_minutes);
        var seconds = Number(s_seconds);
        var all_gettimedate = year * 31557600 + month * 2629800 + date * 86400 + hours * 3600 + minutes * 60 + seconds;

        if (all_gettimedate >= all_opendate) {
            console.log(all_gettimedate+", "+all_opendate);
            return true;
        } else {
            alert("타임캡슐개봉일자가 아닙니다.");
            return false;
        }
    }
</script>
<h3>타임캡슐 목록</h3>
<h3>그룹 개봉일자 : ${findDate}</h3>
<c:forEach items="${timeCapsules}" var="timeCapsule">
    <form action="/timeCapsule/${timeCapsule.id}">
        <p>타임캡슐 타이틀 : ${timeCapsule.title}</p>
        <button type="submit" onclick="return getTime()">타임캡슐 열람</button>
    </form>
</c:forEach>
<form action="/timeCapsule/new">
    <button type="submit">타임캡슐 작성</button>
</form>
</body>
</html>
