<%--
  Created by IntelliJ IDEA.
  User: Mryang
  Date: 2019/6/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<ul>
  <li>
    <span>资源管理</span>
    <ul class="wrap">
      <li>
        <a href="${pageContext.request.contextPath}/addSource.jsp"  target="rightFrame">上传资源</a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/sourceServlet?op=findAllSources" target="rightFrame">查询资源</a>
      </li>
    </ul>
  </li>
</ul>
  </body>
</html>
