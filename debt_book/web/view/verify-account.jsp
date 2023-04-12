
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
        <link rel="icon" href="${pageContext.request.contextPath}/view/ASSESTS/IMG/69.png" type="image/x-icon">
        <title>Verify account</title>

        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

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

    <body class="d-flex h-100 text-center text-white bg-light">

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

            <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
            <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
            </symbol>
            <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
            </symbol>
            <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </symbol>
            </svg>

            <main class="px-3">
                <c:if test="${requestScope.verify}">
                    <div class="alert alert-success d-flex align-items-center" role="alert">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                        <div>
                            <h2>Kích hoạt tài khoản thành công!</h2>
                        </div>
                    </div>
                    <p class="lead d-inline">
                        <a href="../user/login" class="btn btn-lg btn-success fw-bold">Đăng nhập</a>
                    </p>
                </c:if>

                <c:if test="${!requestScope.verify}">

                    <!--<img src="https://cdn.nerdschalk.com/wp-content/uploads/2021/10/failed-759x427.png" alt="fail-img">-->
                    <div class="alert alert-danger  d-flex align-items-center" role="alert">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                        <div class="d-inline">
                            <h2>Kích hoạt tài khoản thất bại!</h2>
                        </div>
                    </div>
                    <p class="lead d-inline" >
                        <a href="../user/register" class="btn btn-lg btn-danger fw-bold ">Đăng ký</a>
                    </p>
                </c:if>

            </main>

            <footer class="mt-auto text-white-50">
                <p>Group 6 - JS1630 - FPT University</a>.</p>
            </footer>
        </div>



    </body>

</html>
