<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>오류페이지</title>
    </head>
    <body>
        <h1>오류가 발생했습니다.</h1>
        <p>${message}</p>
    </body>
</html>
