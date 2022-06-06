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
<body>
<header>타임캡슐 목록</header>
<style type="text/css">

    header {
        display: flex;
        justify-content: center;

    }


    .tg {
        border-collapse: collapse;
        border-spacing: 0;
    }

    .tg td {
        border-style: solid;
        border-width: 0px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
    }

    .tg th {
        border-style: solid;
        border-width: 0px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
    }

    .tg .tg-rivj {
        border-color: #656565;
        color: #000000;
        text-align: center;
        vertical-align: top
    }

    .tg .tg-urjy {
        background-color: #ffce93;
        border-color: #656565;
        color: #000000;
        text-align: center;
        vertical-align: top
    }
</style>
<script>

    var opendate = ${group.opendate};
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
        var today = new date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var date = today.getDate();
        var hours = today.getHours();
        var minutes = today.getMinutes();
        var seconds = today.getSeconds();
        var all_gettimedate = year * 31557600 + month * 2629800 + date * 86400 + hours * 3600 + minutes * 60 + seconds;
        if (all_gettimedate >= all_opendate) {
            var box = '/timeCapsule/{time_capsule_id}';
            return box;

        } else {
            alert("타임캡슐개봉일자가 아닙니다.")
        }
    }
</script>

<c:forEach items="${timeCapsules}" var="timeCapsule">

    <table class="tg" style=" table-layout: fixed; width: 1139px">
        <colgroup>
            <col style="width: 628px">
            <col style="width: 511px">
        </colgroup>
        <thead>
        <tr>
            <th class="tg-urjy">타임캡슐이름</th>
            <th class="tg-urjy">타임캡슐개봉버튼</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="tg-rivj">${timeCapsule.title}</td>
            <td class="tg-rivj"><input type="button" value="개봉" onclick="location.href = getTime()"></td>
        </tr>
        </tbody>
    </table>


    </tr>
</c:forEach>


<br><br>
<input type="button" value="새로운 타임캡슐 생성" onclick="location.href='/timeCapsule/new'"> <br>
</body>
</html>
