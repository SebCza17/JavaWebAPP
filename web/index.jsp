<%@ page import="Model.Entity.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="Resources/CSS/MenuStyle.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Resources/JS/SignInJS.js"></script>

<%

  if(session.getAttribute("user") != null) {
    response.sendRedirect("main.jsp");
  }

%>
<html>
  <head>
    <title>RoomBook</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Anton">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bangers">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
  </head>
  <body>
  <header>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navcol-1">
          <ul class="nav navbar-nav navbar-right">
            <li role="presentation"><a href="">Sign In</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="col-md-12" data-aos="fade-right" data-aos-duration="1800" data-aos-once="true">
      <%@include file="Resources/jspf/SignInFragment.jspf"%>
    </div>
  </header>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>

  </body>
</html>

<style>


  .navbar.navbar-default.navbar-fixed-top {
    background:transparent;
  }

  .navbar-default .navbar-nav > li > a {
    color: #d6d0d2;
    text-transform:uppercase;
  }

  .navbar-default .navbar-nav > li > a:hover {
    color: #d5d626;
  }

  .navbar-brand.navbar-link {
    color: #6f72ff;
  }

</style>