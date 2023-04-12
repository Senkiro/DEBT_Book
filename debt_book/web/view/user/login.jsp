<%-- 
    Document   : login
    Created on : Nov 25, 2022, 10:32:09 PM
    Author     : dell
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/view/ASSESTS/IMG/icon.png" type="image/x-icon">
        <title>Đăng nhập</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <style>
            .divider:after,
            .divider:before {
                content: "";
                flex: 1;
                height: 1px;
                background: #eee;
            }

            .h-custom {
                height: calc(100% - 73px);
            }

            @media (max-width: 450px) {
                .h-custom {
                    height: 100%;
                }
            }
            .img{
                width: 600px;
                height: 400px;
            }
        </style>

    </head>

    <body>
        <section class="vh-100">
            <div class="container-fluid h-custom ">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">

                        <!--                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                                                     class="img-fluid" alt="Sample image">-->
                        <img src="https://t3.ftcdn.net/jpg/03/56/57/18/360_F_356571863_dOVDHBSR8ZEDZifle8mhcPCnrcQNaS03.jpg"
                             class="img" alt="login image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1 card shadow-lg">
                        <form action="login" method="post" class="card-body">

                            <div class="divider d-flex align-items-center my-4">
                                <p class="text-center display-4 fw-bold mx-3 mb-0">Đăng nhập</p>
                            </div>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="text" id="form3Example3" class="form-control form-control-lg"
                                       placeholder="Nhập tên đăng nhập" name="rawUser" required/>
                                <label class="form-label" for="form3Example3">Tên đăng nhập</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                <input type="password" id="form3Example4" class="form-control form-control-lg"
                                       placeholder="Nhập mật khẩu" name="rawPass" required/>
                                <label class="form-label" for="form3Example4">Mật khẩu</label>
                            </div>
                            <div>

                                <c:if test="${requestScope.message ne null}">                                   
                                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                        <strong>${requestScope.message}</strong> 
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>

                                <c:if test="${requestScope.errorLogin ne null}">                                   
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <strong>${requestScope.errorLogin}</strong> 
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                                <!-- Checkbox -->
                                <div class="form-check mb-0">
                                </div>
                                <a href="../user/forget-pwd" class="text-body">Quên mật khẩu</a>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn container-md btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem; background-color: rgb(5, 152, 98); color: #fff">Đăng nhập</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Chưa có tài khoản? <a href="../user/register"
                                                                                                  class="link-danger">Đăng ký</a></p>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </section>

    </body>

</html>