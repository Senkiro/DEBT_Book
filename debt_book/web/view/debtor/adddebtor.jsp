<%-- 
    Document   : adddebtor
    Created on : Feb 15, 2023, 8:13:02 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .form{
            width: 100%;
            height: 300px;
        }
        .txt_field{
            margin: 10px;
            display: flex;
        }
        .txt_field input{
            height:   50px;
            width: 50%;
            border-radius: 1.25rem;
        }
        .button{
            border-radius: 0.25rem;
            background-color: aqua;
            height:   50px;
        }
        .txt_field h3{
            min-width: 100px;
            max-width: 100px;

        }


        .card-body{
            word-wrap: break-word;
        }
        .position-relative {
            position: relative !important;
        }


        .form-group {
            margin-bottom: 1rem;
        }

        .row {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            margin-right: 158px;
            margin-left: 9px;
            align-items: center;
        }
        .form-label-horizontal {
            text-align: right;
        }
        .col-md-9 {
            -ms-flex: 0 0 75%;
            flex: 0 0 75%;
            max-width: 75%;
        }
        .form-control {
            display: block;
            width: 100%;
            height: calc(2.0625rem + 2px);
            padding: 0.375rem 0.75rem;
            font-size: 0.875rem;
            line-height: 1.5;
            color: #5c6873;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #e4e7ea;
            border-radius: 0.25rem;
        }
        .btn-primary {
            color: #fff;
            background-color: #20a8d8;
            border-color: #20a8d8;
        }
        .btn {
            display: inline-block;
            font-weight: 400;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            border: 1px solid transparent;
            padding: 0.375rem 0.75rem;
            font-size: 0.875rem;
            line-height: 1.5;
            border-radius: 0.25rem;
        }

        .btn-secondary {
            color: #23282c;
            background-color: #c8ced3;
            border-color: #c8ced3;
        }
        .col-md-3 {
            -ms-flex: 0 0 25%;
            flex: 0 0 25%;
            max-width: 25%;
            text-align: center;
        }
        .col-md-9 {
            flex: 2 0 79%;
            max-width: 65%;
        }
        .fom{
            border-radius: 10px;
            background-color: #9199b5bf;
            padding: 70px;

        }
    </style>
    <body>
        <h1>Thêm người nợ</h1>

        <!--        <form action="add-debtor" method="post" class="fom">
        
        
                    <div class="position-relative row form-group">
                        <div class="form-label-horizontal col-md-3"><label class="" ><b>Tên</b></label></div>
                        <div class="col-md-9"><input placeholder="Input name" type="text" name="debtorName" required class="form-control">
                        </div>
                    </div>
                    <div class="position-relative row form-group">
                        <div class="form-label-horizontal col-md-3"><label class="" ><b>Địa chỉ</b></label></div>
                        <div class="col-md-9"><input placeholder="Input Address" type="text" name="debtorAddress" required class="form-control">
                        </div>
                    </div><div class="position-relative row form-group">
                        <div class="form-label-horizontal col-md-3"><label class="" ><b>
                                    Số điện thoại</b></label></div>
                        <div class="col-md-9"><input placeholder="Input Phone " type="text" name="phoneNumber"" required class="form-control">
        
                        </div>
                    </div><div class="position-relative row form-group">
                        <div class="form-label-horizontal col-md-3"><label class="" ><b>Email</b></label></div>
                        <div class="col-md-9"><input placeholder="Input Email" type="text" name="debtorEmail" required class="form-control">
        
                        </div>
                    </div>
        
                     <c:if test="${sessionScope.errorAddDebtor ne null}">
                        <h4>${sessionScope.errorAddDebtor}</h4>
                    </c:if>
        
                    <div style="display: flex;
                         justify-content: center;
                         align-items: center;"><button type="submit" class="button"
                              class="mr-1 btn btn-success"><i class="fa fa-plus"></i> Thêm</button></div>
                </form>-->

        <form action="add-debtor" method="post">

            <div>
                <div ><label class="" ><b>Tên</b></label></div>
                <div ><input placeholder="Input name" type="text" name="debtorName" required>
                </div>
            </div>
            <div>
                <div><label class="" ><b>Địa chỉ</b></label></div>
                <div><input placeholder="Input Address" type="text" name="debtorAddress" required >
                </div>
            </div>
            <div>
                <div>
                    <label class="" ><b>Số điện thoại</b></label>
                </div>
                <div><input placeholder="Input Phone " type="text" name="phoneNumber"" required">

                </div>
            </div>
            <div>
                <div>
                    <label class="" ><b>Email</b></label></div>
                <div>
                    <input placeholder="Input Email" type="text" name="debtorEmail" required>
                </div>
            </div>

            <!--debt infor-->
<!--            <div>
                <div ><label  type="text" name="description"><b>Ghi chú </b></label></div>
                
                <div><textarea  rows="8" placeholder="" name="description"></textarea></div>
            </div>
            <div>
                <div ><label class=""><b>Loại nợ (*)</b></label></div>
                <div style="display: flex">
                    -<input type="radio" name="type" checked value="true"
                            style="
                            margin-right: 5px;
                            margin-bottom: 5px;
                            display: flex"></input>
                    <input type="radio" name="type" value="false" style="
                           margin-right: 5px;
                           margin-bottom: 5px;
                           display: flex">+</input></div>
            </div>
            <div >
                <div ><label class="" ><b>Số tiền (*)</b></label></div>
                <div ><input placeholder="Input number" type="number" name="debtValue" required >

                </div>
            </div>-->

            <c:if test="${sessionScope.errorAddDebtor ne null}">
                <h4>${sessionScope.errorAddDebtor}</h4>
            </c:if>

            <div >
                <button type="submit" class="button"><i></i> Thêm</button>
            </div>
        </form>

    </body>
</html>
