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
    <form action="/timecapsule/new" method="post" enctype="multipart/form-data">                                                              
    제목: <input type="text" name = "title">                                                                                                    
                                                                                                                                              
    <br><br>                                                                                                                                  
        그룹:${groupname}<br><br>                                                                                                               
                                                                                                                                              
        멤버:${memberName}<br><br>                                                                                                              
                                                                                                                                              
        타입캡슐내용<br>                                                                                                                            
        <textarea name = "text" cols ="100" rows = "10"                                                                                       
                  placeholder="타임캡슐에 들어갈 내용을 작성해주세요.">                                                                                        
                                                                                                                                              
        </textarea>                                                                                                                           
                                                                                                                                              
        <br><br>                                                                                                                              
                                                                                                                                              
        파일추가삽입<br>                                                                                                                            
                                                                                                                                              
                                                                                                                                              
        <input typt = "file" name = "multipartFiles" multiple="multiple" id = "multipartFiles">                                               
             <br><br>   <br><br>                                                                                                              
                                                                                                                                              
                                                                                                                                              
    <input type="button"  value  = "저장">                                                                              
    </form>                                                                                                                                   
    </body>                                                                                                                                   
</html>                                                                                                                                       
