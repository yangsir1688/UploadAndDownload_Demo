<%--
  Created by IntelliJ IDEA.
  User: Mryang
  Date: 2019/6/16
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
    pageContext.setAttribute("basePath", contextPath);
%>
<html>
<head>
    <title>用户列表</title>
    <script src="${pageContext.request.contextPath}/admin/resource/js/jquery-1.7.2.min.js" type="text/javascript"
            charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/resource/css/style.css"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".click").click(function () {
                $(".tip").fadeIn(200);
            });
            $(".find").click(function () {
                // $("#listform").submit();
                $("#listform").click(function () {
                    window.location.href="${basePath}/navigationServlet?op=findNavigationByLikeName&name="+$("#unm").val();
                });
            });
            $(".tiptop a").click(function () {
                $(".tip").fadeOut(200);
            });
            $(".sure").click(function () {
                <%--window.location = "${basePath}${opServlet}?op=delete&id=" + $(".sure_value").val();--%>
                window.location.href="${basePath}/navigationServlet?op=delNavigation&id="+$(".sure_value").val();
                $(".tip").fadeOut(100);
            });
            $(".cancel").click(function () {
                $(".tip").fadeOut(100);
            });
        });

        function delete_confirm(e, id) {
            $(".tip").fadeIn(200);
            $(".sure_value").val(id);
        }

        function gotoPage(page) {
            $("#pageNo").val(page);
            $("#listform").submit();
        }
    </script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">导航栏管理</a></li>
        <li><a href="#">导航栏列表</a></li>
    </ul>
</div>
<form action="${basePath}/userServlet?op=findUserByLikeName&username=" method="post" id="listform">
    <div class="rightinfo">
        <div class="tools">
            <ul class="toolbar">
                <li style="padding-right: 0px;">
                    &nbsp;&nbsp; 导航栏名称：<input type="text" name="author" id="unm" class="dfinput" style="width: 150px;">
                </li>
                <li class="find">
                    <span><img src="${basePath}/admin/resource/img/t06.png"/> </span>查询
                </li>
            </ul>
        </div>
        <table class="tablelist">
            <thead>
            <tr>
                <th>上传人</th>
                <th>文件名称</th>
                <th>下载次数</th>
                <%--<th>二级链接</th>--%>
                <th>创建时间</th>
                <th width="150px">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sources}" var="item">
                <tr>
                    <td>
                            ${item.author }
                    </td>
                    <td>
                            ${item.sourceName }
                    </td>
                    <td>
                            ${item.downCount }
                    </td>
                    <td>
                            ${item.createTime }
                    </td>
                    <td>
                        <a class="tablelink" href="${pageContext.request.contextPath}/sourceServlet?op=downloadSource&id=${item.id}">【 下载】</a>
                        <a class="tablelink"
                           href="${pageContext.request.contextPath}/navigationServlet?op=updateNavigationView&id=${item.id}">【修改】</a>
                        <a class="tablelink" href="javascript:delete_confirm(this,'${item.id}')">【 删除】</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>



        <div class="tip">
            <div class="tiptop">
                <span>提示信息</span><a></a>
            </div>

            <div class="tipinfo">
                <span><img src="${basePath}/admin/resource/img/ticon.png"/> </span>
                <div class="tipright">
                    <p>是否确认对信息的删除 ？</p>
                    <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
                </div>
            </div>
            <div class="tipbtn">
                <input type="hidden" class="sure_value" value=""/>
                <input name="" type="button" class="sure" value="确定"/>
                &nbsp;
                <input name="" type="button" class="cancel" value="取消"/>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
<%-- <table border="1" width="438">
     <c:forEach items="${list}"  var="l">
         <tr>
             <td>${l.id}</td>
             <td>${l.username}</td>
             <td>${l.birthday}</td>
             <td>
                 <a href="JavaScript:delUser('${l.id}')">删除</a>
                 <a href="${pageContext.request.contextPath}/userServlet?op=editUser&id=${l.id}">修改</a>
             </td>
         </tr>
     </c:forEach>
     <c:if test="${username!=admin}">
         <script>
             alert(98);
         </script>
                 <a href="${pageContext.request.contextPath}/userServlet?op=editUser&id=${l.id}">修改</a>
     </c:if>
 </table>--%>
</body>
</html>
