<%-- 
    Document   : notification
    Created on : Feb 9, 2023, 3:37:33 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="h-100">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <title>Access denied</title>

        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }

            /* Custom default button */
            .btn-secondary,
            .btn-secondary:hover,
            .btn-secondary:focus {
                color: #333;
                text-shadow: none;
                /* Prevent inheritance from `body` */
            }
            body {
                text-shadow: 0 .05rem .1rem rgba(0, 0, 0, .5);
                box-shadow: inset 0 0 5rem rgba(0, 0, 0, .5);
            }

            .cover-container {
                max-width: 42em;
            }
            
            .nav-masthead .nav-link {
                padding: .25rem 0;
                font-weight: 700;
                color: rgba(255, 255, 255, .5);
                background-color: transparent;
                border-bottom: .25rem solid transparent;
            }

            .nav-masthead .nav-link:hover,
            .nav-masthead .nav-link:focus {
                border-bottom-color: rgba(255, 255, 255, .25);
            }

            .nav-masthead .nav-link+.nav-link {
                margin-left: 1rem;
            }

            .nav-masthead .active {
                color: #fff;
                border-bottom-color: #fff;
            }
        </style>
        <!-- Custom styles for this template -->
        <!-- <link href="cover.css" rel="stylesheet"> -->
    </head>

    <body class="d-flex h-100 text-center text-white bg-dark">

        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <header class="mb-auto">
              <div>
                <h3 class="float-md-start mb-0"></h3>
<!--                <nav class="nav nav-masthead justify-content-center float-md-end">
                  <a class="nav-link active" aria-current="page" href="#">Home</a>
                  <a class="nav-link" href="#">Features</a>
                  <a class="nav-link" href="#">Contact</a>
                </nav>-->
              </div>
            </header>

            <main class="px-3">
                <h1>Access denied!</h1>
                <c:if test="${sessionScope.notification ne null}">
                    <p class="lead">${sessionScope.notification}</p>
                                         
                        <p class="lead d-inline">
                            <a href="user/login" class="btn btn-lg btn-secondary fw-bold border-white bg-white">Login</a>
                        </p>
                        <p class="lead d-inline" >
                            <a href="user/register" class="btn btn-lg btn-secondary fw-bold border-white bg-white">Register</a>
                        </p>
                    
                </c:if>
            </main>

            <footer class="mt-auto text-white-50">
                <p>Group 6 - JS1630 - FPT University.</p>
            </footer>
        </div>

    </body>

</html>