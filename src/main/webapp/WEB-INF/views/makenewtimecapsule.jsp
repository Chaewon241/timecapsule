<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>                                
<!DOCTYPE html>                                                                                                        
<html>                                                                                                                 
<head>                                                                                                                 
    <meta charset="utf-8">                                                                                             
    <meta name="viewport"                                                                                              
          content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0"> 
    <link rel="stylesheet" href="style.css">                                                                           
    <title>타임캡슐작성</title>
    <style>
        form{
            margin-top:100px;
            margin-left:300px;
            margin-right:300px;

        }
        input[type=submit]{
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
       
        header{
            display:flex;
            justify-content: center;
        }

    </style>
</head>                                                                                                                
<body>
<header>타임캡슐작성</header>

<form action="/timeCapsule/new" method="post" enctype="multipart/form-data">                                           
    제목: <input type="text" name="title">                                                                               
                                                                                                                       
    <br><br>                                                                                                           
    그룹:${groupName}<br><br>                                                                                            
                                                                                                                       
    멤버:${memberName}<br><br>                                                                                           
                                                                                                                       
    <text>타입캡슐내용</text><br>                                                                                                         
    <textarea name="text" cols="100" rows="10"                                                                         
              placeholder="타임캡슐에 들어갈 내용을 작성해주세요.">                                                                     
                                                                                                                       
        </textarea>                                                                                                    
                                                                                                                       
    <br><br>                                                                                                           
                                                                                                                       
    파일추가삽입<br>                                                                                                         
    <text>추가할 파일의 확장자는 .mp4,.webm,.wav,.avi,.jpg,.png,.gif,.bmp까지만 업로드 가능합니다.</text>
                                                                                                                       
    <input type="file" name="multipartFiles" multiple="multiple" id="multipartFiles" accept=".mp4,.webm,.wav,.avi,.jpg,.png,.gif,.bmp">
    <br><br> <br><br>                                                                                                  
                                                                                                                       
                                                                                                                       
    <input type="submit" value="저장">                                                                                   
</form>                                                                                                                
</body>                                                                                                                
</html>     
