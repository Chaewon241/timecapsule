<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
        <title>타임캡슐개봉</title>
    </head>
    <body>
        ${msg}
        제목: ${timeCapsule.title}<br>
        그룹명: ${timeCapsule.group.groupName}<br>
        그룹리더: ${timeCapsule.group.getLeader()}<br><br>
        내용 <br>
        ${timeCapsule.text}
        <br><br>

        첨부 파일<br>
        <c:forEach items="${timeCapsule.files}" var="file">

            <p id="box">
            </p>
            <script>
              var src = "/images/${file.getStoreFileName()}";
              var ext = extractExt(src);
              if (ext === "mp4" || ext === "wmv" || ext === "avi" || ext === "webm") {
                console.log("동영상 파일입니다");
                var video = document.createElement('video');
                video.src = src;
                video.autoplay = false;
                video.controls = true;
                video.muted = false;
                video.height = 240;
                video.width = 320;
                document.getElementById('box').appendChild(video);
              } else if (ext === "jpg" || ext === "gif" || ext === "png" || ext === "bmp") {
                console.log("이미지 파일입니다");
                var img = document.createElement('img');
                img.src = src;
                img.height = 240;
                img.width = 320;
                document.getElementById('box').appendChild(img);
              } else {
                document.getElementById('info').innerHTML = "열 수 없는 확장자의 파일입니다.";
              }

              function extractExt(filename) {
                var pos = filename.lastIndexOf(".");
                return filename.substring(pos + 1);
              }
            </script>
        </c:forEach>
        <br>
        타임캡슐 열람 가능일:
        <text>${timeCapsule.group.openDate}</text>
        <br>
        <br><br>
    </body>
</html>