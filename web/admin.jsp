<%@ page import="Model.Entity.UserEntity" %>
<%@ page import="Model.DAO.UserGroupDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="Resources/CSS/MenuStyle.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Resources/JS/SignInJS.js"></script>

<%

    UserEntity userEntity = new UserEntity();
    if(session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }else {
        userEntity = (UserEntity) session.getAttribute("user");
        if(!UserGroupDAO.getPermision(userEntity.getId()).equals("admin"))
            response.sendRedirect("main.jsp");
    }




%>
<html>
<head>
    <meta charset="UTF-8">
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
                    <li role="presentation"><a href="main.jsp">Main Menu</a></li>
                    <li role="presentation"><a href="main.jsp?faculty=-1">All Classes</a></li>
                    <li role="presentation"><a href="user.jsp">User Panel</a></li>
                    <% if(UserGroupDAO.getPermision(userEntity.getId()).equals("admin")){ %>
                    <li role="presentation"><a href="admin.jsp">Admin Panel</a></li>
                    <%}%>
                    <li role="presentation"><a href="LogOutServlet">Sign Out</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="col-md-12" data-aos="fade-right" data-aos-duration="1800" data-aos-once="true">

        <%if(request.getParameter("formEditID") == null && request.getParameter("formClassID") == null){ %> <%@include file="Resources/jspf/AdminFragment.jspf"%> <%}
    else if(request.getParameter("formClassID") == null) { %> <%@include file="Resources/jspf/EditAdminFragment.jspf"%> <%}
    else { %> <%@include file="Resources/jspf/EditClassFragment.jspf"%> <%}%>

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