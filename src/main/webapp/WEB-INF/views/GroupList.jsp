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
            <%--const  showValue = (target) => {--%>
            <%--    const text = target.options[target.selectedIndex].text;--%>
            <%--    document.querySelector(`div`).innerHTML = `text: ${text} value: ${value}`;--%>
            <%--    document.getElementById('selname').innerHTML--%>
            <%--}--%>
        </script>
        <header>
            <h1>그룹 목록</h1>

        </header>
        <form action="/group/{id}" method="get">
            <select name = "sel"  multiplesize = "2" onchange="showValue(this)">
                <option value="groupname">그룹명</option>
                <option value="leadername">리더명</option>
            </select>
            <div id="selname"></div>
            <a href="">${groups}</a>
            <button type="submit">조회</button>
        </form>
    </body>
</html>