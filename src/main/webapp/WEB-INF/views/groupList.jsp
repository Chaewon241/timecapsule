<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8" name="viewport"
              content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <style>
            header, footer{
                display:flex;
                justify-content: center;
            }
            form{
                margin-top: 100px;
                margin-left:300px;
                margin-right:300px;
            }

            .input-box{
                position:relative;
                margin:10px 0;
            }
            .input-box > input{
                background:transparent;
                border:none;
                border-bottom: solid 1px #ccc;
                padding:20px 0px 5px 0px;
                font-size:14pt;
                width:100%;
            }
            input::placeholder{
                color:transparent;
            }
            input:placeholder-shown + label{
                color:#aaa;
                font-size:14pt;
                top:15px;

            }
            input:focus + label, label{
                color:#8aa1a1;
                font-size:10pt;
                pointer-events: none;
                position: absolute;
                left:0px;
                top:0px;
                transition: all 0.2s ease ;
                -webkit-transition: all 0.2s ease;
                -moz-transition: all 0.2s ease;
                -o-transition: all 0.2s ease;
            }

            input:focus, input:not(:placeholder-shown){
                border-bottom: solid 1px #8aa1a1;
                outline:none;
            }

            button{
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

            h3{
                display:flex;
                justify-content: center;
                margin:50px 0px;
            }

            hr{
                border: 0;
                height: 3px;
                margin:50px 0px;
                background: #ccc;
            }
        </style>
        <title>?????? ??????</title>
    </head>

    <body>
        <header>
            <h1>?????? ??????</h1>
        </header>
        <form action="/groupList" method="post" accept-charset="utf-8">
            <select name="sel" multiplesize="2">
                <option value="groupname">?????????</option>
                <option value="leadername">?????????</option>
            </select>
            <div class="input-box">
                <input id="searchText" type="text" name="searchText" placeholder="????????? ??? ??????">
                <label for="searchText">????????? ??? ??????</label>
            </div>

            <button type="submit">??????</button>
        </form>
        <hr>
        <section id="sec">
            <h3>?????? ?????????</h3>
            <c:forEach items="${groups}" var="group">
                <form action="/group/${group.id}/join" method="post">
                    <p>??????ID : ${group.id}, ???????????? : ${group.groupName}</p>
                    <p>???????????? : ${group.getLeader()}</p>
                    <p>???????????? ???????????? : ${group.openDate}</p>
                    <div class="input-box">
                        <input id="password" type="password" name="password" placeholder="????????????">
                        <label for="password">????????????</label>
                    </div>
                    <button type="submit">????????????</button>
                </form>
            </c:forEach>
        </section>
        <footer>
            <a href="/main2">?????????</a>
        </footer>
    </body>
</html>