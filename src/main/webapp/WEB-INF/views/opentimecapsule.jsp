<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>타임캡슐개봉</title>
    <style type="text/css">



        form {
            margin-top: 100px;
            margin-left: 300px;
            margin-right: 300px;
        }

        header {

            display: flex;

            justify-content: center;

        }

    </style>
</head>
<body>
<form action="/timeCapsule/Group/{group_id}" method="get">
    <header>타임캡슐개봉</header>
    제목:${title}<br>
    그룹명:${timeCapsule.group.groupName}<br>
    그룹리더:${timeCapsule.group.getLeader()}<br>
    그룸멤버<br>
    <c:forEach items="${timeCapsule.group.groupMember}" var="group">

        <p>${group.getNickname()}
        </p>

    </c:forEach><br><br>

    내용 <br>
    ${text}


    <br><br>

    첨부파일<br>
    <c:forEach items="${timeCapsule.files}" var="file">
        <tr>
            <td>
                <div id="box"></div>
                <script>
                    var src = "/images/${file.getStoreFileName()}"
                    var ext = extractExt(src);
                    if (ext === "mp4" || ext === "wmv" || ext === "avi" || ext === "webm") {
                        const video = document.createElement('video');
                        video.src = src;
                        video.autoplay = false;
                        video.controls = true;
                        video.muted = false;
                        video.height = 240;
                        video.width = 320;
                        const box = document.getElementById('box');
                        box.appendChild(video);
                    } else if (ext === "jpg" || ext === "gif" || ext === "png" || ext === "bmp") {
                        const image = document.createElement('image');
                        image.src = src;
                        image.height = 240;
                        image.width = 320;
                        const box = document.getElementById('box');
                        box.appendChild(image);

                    } else {
                        println("첨부된 파일이 없습니다.")
                    }

                    function extractExt(filename) {
                        var pos = filename.lastIndexOf(".");
                        return filename.substring(pos + 1);
                    }
                </script>
        </tr>
        </td>

    </c:forEach>


    <br>
    <br>


    타임캡슐 열람날짜:
    ${group.opendate}
    <br>
    <br><br>
</form>
</form>
</body>


</html>
