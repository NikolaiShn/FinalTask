<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>Регистрация</title>
</head>
<body>
<div>
    <div>
       <form method="POST" action="/registration">
         <input name="username" type="text" placeholder="Username"/>
         <input name="password" type="password" placeholder="Password"/>
         <select name="role" id="role">
           <option value="ADMIN">ADMIN</option>
           <option value="USER">USER</option>
         </select>
         <button type="submit">Зарегистрироваться</button>
       </form>
    </div>
</div>
</body>
</html>