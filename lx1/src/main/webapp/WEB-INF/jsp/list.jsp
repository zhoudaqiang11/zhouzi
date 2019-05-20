<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index_likek.css">
</head>
<body>
<table>
  <tr>
    <th>编号</th>
    <th>姓名</th>
    <th>入学日期</th>
    <th>学院</th>
    <th>性别</th>
    <th>宿舍人数</th>
    <th>宿舍编号:</th>
  </tr>
  <c:forEach items="${paramlist}" var="p">
    <tr>
      <td>${p.sid}</td>
      <td>${p.sname}</td>
      <td>${p.sdate}</td>
      <td>${p.collage}</td>
      <td>${p.sex}</td>
      <td>${p.rnum}</td>
      <td>${p.rname}</td>
    </tr>
  </c:forEach>

</table>
</body>
</html>