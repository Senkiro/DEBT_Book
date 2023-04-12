<%-- Document : profile Created on : Mar 12, 2023, 4:30:05 PM Author : NGO --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
        <title>Profile</title>
        <link rel="stylesheet"
              href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/view/CSS/css-debtor.css">
        <style>
            .page-content {
                margin: 36px auto;
                border-radius: 12px;
                padding: 24px 16px;
                max-width: 550px;
            }

            .mt-4 {
                margin-top: 12px;
            }

            .text-light {
                font-weight: 400;
            }
        </style>
    </head>

    <body>
        <script>
            <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
        </script>
        
        <input type="checkbox" id="menu-toggle">
        <div class="sidebar">
            <div class="side-header">
                <h3>T<span>ask bar</span></h3>
            </div>

            <div class="side-content">
                <div class="profile">
                    <div class="profile-img bg-img" style="background-image: url(img/3.jpeg)"></div>
                    <h4>${sessionScope.user.username}</h4>
                </div>

                <div class="side-menu">
                    <ul>
                        <li>
                            <a href="../home">
                                <span class="las la-home"></span>
                                <small>Home</small>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="active">
                                <span class="las la-user-alt"></span>
                                <small>Profile</small>
                            </a>
                        </li>
                        <li>
                            <a href="../debtor">
                                <span class="las la-clipboard-list"></span>
                                <small>Sổ ghi nợ</small>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>


        <div class="main-content">
            <header>
                <div class="header-content">
                    <label for="menu-toggle" style="font-size: 20px">
                        <span class="las la-bars"></span>
                    </label>

                    <div class="header-menu">
                        <div class="">
                            <button type="button" class="btn btn-default btn-lg"
                                    data-toggle="modal" data-target="#logout">Đăng xuất</button>
                        </div>
                    </div>
                </div>
            </header>

            <main>
                <form action="profile" method="POST" class="needs-validation" novalidate>

                    <div class="page-header">
                        <h1>Thông tin cá nhân</h1>
                    </div>

                    <div class="page-content">
                        <div class="col-12 mt-4">
                            <label for="abcxyz" class="form-label">Username</label>
                            <input class="form-control" class="form-control" name="abcxyz" type="text"
                                   value="${sessionScope.user.username}" disabled="" />
                        </div>
                        <div class="col-12 mt-4">
                            <input class="form-control" name="username" type="hidden"
                                   value="${sessionScope.user.username}" />
                            <label>Display name</label>
                            <input class="form-control" name="displayName" type="text"
                                   value="${sessionScope.user.displayName}" />
                        </div>
                        <div class="col-12 mt-4">
                            <label>Gender</label>
                            <div class="form-check">
                                <input class="form-check-input" name="gender" type="radio" value="1"
                                       ${sessionScope.user.gender eq true ? 'checked' : ''}/>
                                <label class="form-check-label text-light" for="gender">Male</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" name="gender" type="radio" value="0"
                                       ${sessionScope.user.gender eq false ? 'checked' : ''} />
                                <label class="form-check-label text-light" for="gender">Female</label>
                            </div>
                        </div>

                        <div class="col-12 mt-4">
                            <label>DOB</label>
                            <input class="form-control" name="dob" type="date"
                                   value="${sessionScope.user.dob}" />
                        </div>

                        <div class="col-12 mt-4">
                            <label>Address</label>
                            <input class="form-control" name="address" type="text"
                                   value="${sessionScope.user.address}" />
                        </div>

                        <hr />

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Save</button>
                        </div>
                    </div>
                </form>
            </main>

        </div>
        <div class="modal fade" id="logout" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Xác nhận</h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default col-md-5" data-dismiss="modal">Đóng</button>
                        <span class="col-md-1"></span>
                        <a href="logout" class="btn btn-danger col-md-5">Đăng xuất</a>

                    </div>
                </div>
            </div>
        </div>

    </body>

</html>