<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0"> 
        <link rel="stylesheet" href="style.css">
        <title> 타임캡슐날짜수정</title>

    </head>
    <body>
    <form action="/groupList" method ="get">
        <select name = "selgroup" multiple = "2" onchange="showValue(this)">
            <option value="groupname">그룹명</option>
        </select>
    </form>
    <form action = "/timeCapSule/group/{group_id}" method = "get">
        <input type ="button" name ="getTimecapsuleList" value = "조회">
        <form action = "/group/{id}" method = "post">
            <style type="text/css">
                .tg  {border-collapse:collapse;border-spacing:0;}
                .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
                    overflow:hidden;padding:10px 5px;word-break:normal;}
                .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
                    font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
                .tg .tg-x5du{background-color:#ffce93;border-color:#efefef;text-align:left;vertical-align:top}
                .tg .tg-ly6r{border-color:#efefef;text-align:left;vertical-align:top}
            </style>
            <table class="tg">
                <thead>
                <tr>
                    <th class="tg-x5du">제목</th>
                    <th class="tg-x5du">타임캡슐개봉예정일</th>
                    <th class="tg-x5du">타임캡슐 개봉 예정일 수정</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td class="tg-ly6r">${title}</td>
                    <td class="tg-ly6r">${opendate}</td>
                    <td class="tg-ly6r"><input type = "date" min = "2022-05-30" max = "2025-05-30" name = "updateOpenDate" step = "1"><input type = "submit" value ="수정"></td>
                </tr>
                </tbody>
            </table>
        </form>
    </form>






    </body>
</html>
