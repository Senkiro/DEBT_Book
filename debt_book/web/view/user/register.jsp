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
        <link rel="icon" href="${pageContext.request.contextPath}/view/ASSESTS/IMG/69.png" type="image/x-icon">
        <title>Register</title>

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
            .image{
                margin-left: 20px;
                margin-right: 40px;
                width: 100px;
                height: 60px;
            }
            .img{
                width: 500px;
                height: 500px;
            }
        </style>

    </head>

    <body>
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex align-items-center justify-content-center h-100">
                    <div class="col-md-8 col-lg-7 col-xl-6">
                        <img src="https://scontent.fhan18-1.fna.fbcdn.net/v/t1.15752-9/336202973_627415039214183_2467485639861428203_n.png?_nc_cat=102&ccb=1-7&_nc_sid=ae9488&_nc_ohc=RJbbP3MH_n8AX8YrGlZ&_nc_ht=scontent.fhan18-1.fna&oh=03_AdRDlcPxIXgE-8MMVFhqBQkxlRnp7IJa0aSkcMPbgCV6zA&oe=643691E3"
                             class="img" alt="Phone image">
                    </div>
                    <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1 card shadow-lg">
                        <form action="register" method="post" class="card-body">

                            <div class="divider d-flex align-items-center my-4">
                                <p class="text-center display-4 fw-bold mx-3 mb-0">Đăng ký</p>
                            </div>

                            <c:if test="${requestScope.error ne null}">                                   
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <strong>${requestScope.error}</strong> 
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.message ne null}">                                   
                                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>${requestScope.message}</strong> 
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>

                            <!-- username input -->
                            <div class="form-outline mb-4">
                                <input type="text" id="form1Example23" class="form-control form-control-lg" 
                                       placeholder="Nhập tên đăng nhập" name="rawUser" required/>
                                <label class="form-label" for="form1Example23">Tên đăng nhập</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="form1Example23" class="form-control form-control-lg" 
                                       placeholder="Nhập mật khẩu" name="rawPass" required/>
                                <label class="form-label" for="form1Example23">Mật khẩu</label>
                            </div>

                            <!-- Confirm password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="form1Example23" class="form-control form-control-lg" 
                                       placeholder="Nhập lại mật khẩu" name="rawConfirm" required/>
                                <label class="form-label" for="form1Example23">Xác nhận mật khẩu</label>
                            </div>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="email" id="form1Example13" class="form-control form-control-lg" 
                                       placeholder="Nhập email" name="rawEmail" required/>
                                <label class="form-label" for="form1Example13">Email</label>
                            </div>

                            <div class="d-flex">
                                <img src="../captcha" alt="Captcha image" class="image img-fluid">
                                <div class="form-outline mb-4">
                                    <input type="text" id="form1Example13" class="form-control form-control-lg" 
                                           placeholder="Nhập captcha" name="captcha" required/>
                                    <label class="form-label" for="form1Example13">Captcha</label>
                                </div>                              
                            </div>


                            <!--jsp code-->            

                            <div class="d-flex justify-content-around align-items-center mb-4">
                                <a href="../user/forget-pwd">Quên mật khẩu?</a>
                            </div>

                            <!-- Submit button -->
                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn container-md btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem; background-color: rgb(5, 152, 98); color: #fff">Đăng ký</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0"><a href="../user/login"
                                                                                      class="link-danger">Đăng nhập</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>

</html>
