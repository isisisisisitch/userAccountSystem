<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html>
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>modify user</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">modify user</h3>
        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <!--  隐藏域 提交id-->
            <input type="hidden" name="id" value="${user.id}">

          <div class="form-group">
            <label for="name">name：</label>
            <input type="text" class="form-control" id="name" name="name"  value="${user.name}" readonly="readonly" placeholder="请输入姓名" />
          </div>

          <div class="form-group">
            <label>gender：</label>
              <c:if test="${user.gender == 'M'}">
                  <input type="radio" name="gender" value="M" checked />M
                  <input type="radio" name="gender" value="F"  />F
              </c:if>

              <c:if test="${user.gender == 'F'}">
                  <input type="radio" name="gender" value="M"  />M
                  <input type="radio" name="gender" value="F" checked  />F
              </c:if>


          </div>

          <div class="form-group">
            <label for="age">age：</label>
            <input type="text" class="form-control" value="${user.age}" id="age"  name="age" placeholder="plz input age" />
          </div>

          <div class="form-group">
            <label for="address">address：</label>
             <select name="address" id="address" class="form-control" >
                 <c:if test="${user.address == 'van'}">
                    <option value="van" selected>van</option>
                    <option value="mtl">mtl</option>
                    <option value="trt">trt</option>
                 </c:if>

                 <c:if test="${user.address == 'mtl'}">
                     <option value="van" selected>van</option>
                     <option value="mtl">mtl</option>
                     <option value="trt">trt</option>
                 </c:if>

                 <c:if test="${user.address == 'trt'}">
                     <option value="van" selected>van</option>
                     <option value="mtl">mtl</option>
                     <option value="trt">trt</option>
                 </c:if>
            </select>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" id="qq" class="form-control" value="${user.qq}" name="qq" placeholder="plz input qq"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" value="${user.email}" name="email" placeholder="plz input email"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="submit" />
                <input class="btn btn-default" type="reset" value="reset" />
                <input class="btn btn-default" type="button" value="back"/>
             </div>
        </form>
        </div>
    </body>
</html>