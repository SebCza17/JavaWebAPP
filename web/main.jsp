<%@ page import="Model.Entity.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="Resources/CSS/MenuStyle.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Resources/JS/SignInJS.js"></script>

<%

    UserEntity userEntity = null;
    if(session.getAttribute("user") == null) {
        response.sendRedirect("");
    }else
        userEntity = (UserEntity) session.getAttribute("user");


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
            <div class="navbar-header"><a class="navbar-brand navbar-link" href="https://www.instagram.com/dize_designer/" target="blank"><i class="material-icons">cast</i></a>
                <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
            </div>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav navbar-right">
                    <li role="presentation"><a href="#">start now</a></li>
                    <li role="presentation"><a href="#">how to use</a></li>
                    <li role="presentation"><a href="#">online streamers</a></li>
                    <li role="presentation"><a href="#">sign in</a></li>
                    <li role="presentation"><a href="LogOutServlet">Sign Out</a></li>
                </ul>
            </div>
        </div>
    </nav>
            <div class="col-md-12" data-aos="fade-right" data-aos-duration="1800" data-aos-once="true">
                <%@include file="Resources/jspf/BodyFragment.jspf"%>
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