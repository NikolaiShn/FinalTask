<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>Главная</title>
</head>
<body>
<div>
    <h4><a href="/login">Войти</a></h4>
    <div>
          <form method="POST" action="/logout">
            <h2>Выход из системы</h2>
            <button type="submit">Logout</button>
          </form>
    </div>
    <h4><a href="/registration">Зарегистрироваться</a></h4>
</div>
</body>
</html>