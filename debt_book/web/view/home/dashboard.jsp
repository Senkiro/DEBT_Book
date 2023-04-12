<%-- 
    Document   : profile
    Created on : Mar 12, 2023, 4:30:05 PM
    Author     : NGO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
        <link rel="icon" href="${pageContext.request.contextPath}/view/ASSESTS/IMG/icon.png" type="image/x-icon">
        
        <title>Trang chủ</title>
        <style>
            /* 
    ### Primary
    
    - Dark Blue: hsl(233, 26%, 24%)
    - Lime Green: hsl(136, 65%, 51%)
    - Bright Cyan: hsl(192, 70%, 51%)
    
    ### Neutral
    
    - Grayish Blue: hsl(233, 8%, 62%)
    - Light Grayish Blue: hsl(220, 16%, 96%)
    - Very Light Gray: hsl(0, 0%, 98%)
    - White: hsl(0, 0%, 100%) */

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            .nav-bar {
                display: flex;
                justify-content: space-around;
                align-items: center;
                padding-top: 20px;
                padding-bottom: 20px;
            }
            .nav-items > a {
                text-decoration: none;
                font-size: 1.2rem;
                font-weight: 600;
                color: hsl(233, 8%, 62%);
                margin-left: 10px;
            }
            .nav-bar > button {
                padding: 12px 28px;
                border: none;
                outline-width: 0;
                color: white;
                border-radius: 24px;
                cursor: pointer;
                font-weight: 400;
                background-image: linear-gradient(
                    120deg,
                    hsl(192, 70%, 51%),
                    hsl(136, 65%, 51%)
                    );
            }
            .hero-section {
                display: flex;
                justify-content: space-around;
                align-items: center;
                height: 90vh;
            }
            .hero-img-container {
                background-image: url('./images/bg-intro-desktop.svg');
                background-size: cover;
                background-position: bottom;
                background-repeat: no-repeat;
                padding-top: 20px;
            }
            .hero-img-container > img {
                width: 500px;
            }
            .hero-text-container {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: flex-start;
            }
            .hero-text-container > h1 {
                font-size: 70px;
                font-weight: 800;
                margin-bottom: 20px;
                color: hsl(233, 26%, 24%);
                line-height: 1.1;
            }
            .hero-text-container > p {
                color: hsl(233, 8%, 62%);
                margin-bottom: 15px;
                font-weight: 700;
            }
            .hero-text-container > button {
                padding: 12px 28px;
                border: none;
                outline-width: 0;
                color: white;
                border-radius: 24px;
                cursor: pointer;
                font-weight: 400;
                background-image: linear-gradient(
                    120deg,
                    hsl(192, 70%, 51%),
                    hsl(136, 65%, 51%)
                    );
            }
            .container {
                background-color: hsl(220, 16%, 96%);
            }


        </style>
    </head>
    <body>
        <nav class="nav-bar">
            <img src="./images/logo.svg" alt="" />
            <div class="nav-items">
                <a href="">Home</a>

            </div>
            <button type="button" class="btn btn-talk" data-toggle="modal" data-target="#logout"><a href="logout">Đăng xuất</a></button>
        </nav>
        <header class="hero-section">
            <div class="hero-text-container">
                <h1>DEBTBOOK<br/>
                    ONLINE</h1>
                <p>Với DEBTBOOK, quản lí khoản nợ chưa bao giờ dễ dàng như thế.</p>
                <P>Ghi chép công nghệ chính xác tuyệt đối</P>
                <P>Theo dõi lịch sử giao dịch chi tiết</P>


                <button><a class="btn btn-lg btn-success" href="debtor">Start</a></button>
                <p></p>

            </div>
            <div class="hero-img-container">
                <img src="https://svgshare.com/i/JpQ.svg" alt="" />
            </div>
        </header>

        <div class="modal fade" id="logout" role="dialog">
            <div class="modal-dialog modal-sm">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">Xác nhận</h4>
                </div>
                <div class="modal-footer btn-group d-flex justify-content-center">
                  <button type="button" class="btn btn-default " data-dismiss="modal">Đóng</button>
                  
                  <a href="logout" class="btn btn-danger">Đăng xuất</a>
                  
                </div>
              </div>
            </div>
          </div>
    </body>
</html>
