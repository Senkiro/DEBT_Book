<%-- 
    Document   : debtorlist
    Created on : Feb 9, 2023, 7:28:08 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
        <link rel="icon" href="${pageContext.request.contextPath}/view/ASSESTS/IMG/icon.png" type="image/x-icon">
        <title>Danh sách người nợ</title>

        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script> 
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/CSS/css-debtor.css">

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        
        <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <link href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
        
        <style>
            input[type="number"]{
                width: 90px;
                /*width: auto;*/
            }
            input[type="text"]{
                /*width: 130px;*/
                width: auto;
            }
            .action{
                display: flex;

                justify-content: center;
                /*margin: auto;*/
            }
            #content-table{
                overflow-y: scroll;
                overflow-x: scroll;
            }
            #content-table{
                width: auto;
            }
            
        </style>

    </head>

    <body>
        <script>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        </script>
        
        <input type="checkbox" id="menu-toggle">
        <div class="sidebar">
            <div class="side-header">
                <h3>T<span>ask bar</span></h3>
            </div>

            <div class="side-content">
                
                <div class="profile">
                    <div class="profile-img bg-img" style="background-image: url(${pageContext.request.contextPath}/view/ASSESTS/IMG/icon.png)"></div>
                    <h4>${sessionScope.user.username}</h4>
                </div>
                
                <div class="side-menu">
                    <ul>
                        <li>
                            <a href="home" >
                                <span class="las la-home"></span>
                                <small>Home</small>
                            </a>
                        </li>
                        <li>
                            <a href="user/profile">
                                <span class="las la-user-alt"></span>
                                <small>Profile</small>
                            </a>
                        </li>
                        <li>
                            <a href="user/changepwd">
                                <span class="las la-redo-alt" ></span>
                                <small>Đổi mật khẩu</small>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="active">
                                <span class="las la-clipboard-list active" ></span>
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
                <form action="debtor" method="POST">
                    <div class="page-content">

                        <div class="records table-responsive">

                            <div class="record-header">
                                <div class="add">
                                    <a href="#addDeptor" class="btn btn-success" data-toggle="modal" ><span>Thêm người nợ</span></a>
                                    <!--                                    <button>Add record</button>-->
                                </div>

                                <div class="" style="display: flex;">
                                    <!--<div class="d-flex d-inline">-->
                                        <button type="button" onclick="moveBefore()" class="btn btn-success" style="margin-right: 20px">Trang trước</button>
                                        <label style="margin: auto">Choose page:</label>
                                        <input class="form-control" oninput="filter()" type="number" value="${sessionScope.pageIndex}" name="pageIndex" id="page-index" class="" min="1">
                                        <nav style="margin: auto; margin-right: 20px" id="max-page" value=""></nav>
                                        <button type="button" onclick="moveNext()" class="btn btn-success" style="margin-right: 20px">Trang sau</button>
                                        
                                    <!--</div>-->

                                    <label style="margin: auto; margin-right: 10px">Số bản ghi:</label>                           
                                    <select class="form-select" onchange="filter()" id="page-size" name="pageSize">
                                        <option value="5">5 records</option>
                                        <option value="10" selected>10 records</option>
                                        <option value="25">25 records</option>
                                        <option value="50">50 records</option>
                                    </select>

                                </div>
                            </div>

                            <div>
                                <section class="table_body ">
                                    <table id="debtorList" class="table table-striped table-hover table-responsive" width="98%">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th>Tên</th>
                                                <th>Địa chỉ</td>
                                                <th>Số điện thoại</th>
                                                <th>Email</th>
                                                <th>Tổng nợ</th>
                                                <th>Ngày tạo</th>
                                                <th>Ngày cập nhật</th>
                                                <th class="action">Action</th>
                                            </tr>
                                            <tr>
                                                <td>                                                  
                                                    <input oninput="filter()" class="form-control" type="number" id="idFrom-filter" name="filterIdFrom" placeholder="id from"> <br><!-- comment -->
                                                    <input oninput="filter()" class="form-control" type="number" id="idTo-filter" name="filterIdTo" placeholder="id to">
                                                </td>                                              
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="name-filter" type="text" name="filterName" placeholder="Tên">
                                                </td>
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="address-filter" type="text" name="filterAddress" placeholder="Địa chỉ">
                                                </td>
                                                <td>                                                  
                                                    <input oninput="filter()" class="form-control" type="number" id="phone-filter" name="filterPhoneNumber" placeholder="Số điện thoại">
                                                </td>
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="email-filter" type="text" name="filterEmail" placeholder="Email">
                                                </td>
                                                <td>                                                  
                                                    <input oninput="filter()" class="form-control" type="number" id="totalDebtFrom-filter" name="filterTotalDebtFrom" placeholder="Tổng nợ "> <br>
                                                    <input oninput="filter()" class="form-control" type="number" id="totalDebtTo-filter" name="filterTotalDebtTo" placeholder="Tổng nợ ">
                                                </td>
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="createDateFrom-filter" type="date" name="filterCreateDateFrom" placeholder=""> <br>
                                                    <input oninput="filter()" class="form-control" id="createDateTo-filter" type="date" name="filterCreateDateTo" placeholder="">
                                                </td>
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="updateDateFrom-filter" type="date" name="filterUpdateDateFrom" placeholder=""> <br>
                                                    <input oninput="filter()" class="form-control" id="updateDateTo-filter" type="date" name="filterUpdateDateTo" placeholder="">
                                                </td>
                                                <td class="action">
                                                    <button type="button" onclick="clearFilter(); filter()" class="btn container-md btn-danger btn-lg" id="clear-filter">Xóa bộ lọc</button>
                                                </td>
                                            </tr>
                                        </thead>
                                        <tbody id="content-table">

                                            <c:forEach begin="0" end="${sessionScope.pageSize - 1}" items="${sessionScope.debtorList}" var="debtor">

                                                <tr>
                                                    <td>${debtor.debtorId}</td>
                                                    <td>${debtor.debtorName}</td>
                                                    <td>${debtor.debtorAddress}</td>
                                                    <td>${debtor.phoneNumber}</td>
                                                    <td>${debtor.debtorEmail}</td>
                                                    <td>
                                                        <strong class="debt">
                                                            <fmt:formatNumber  value="${debtor.totalDebt}" type="currency" currencyCode="VND"/>
                                                        </strong>
                                                    </td>
                                                    <td>${debtor.createDate}</td>
                                                    <td>${debtor.updateDate}</td>

                                                                                                            
                                                        <td>
                                                            <a href="debt?debtorId=${debtor.debtorId}" class="btn btn-info">
                                                               <i class="material-icons" data-toggle="tooltip" title="Edit">&#xe88f;</i>
                                                               info
                                                            </a>
                                                            
                                                            <a href="insertdebt?debttorId=${debtor.debtorId}" class="btn btn-success" data-toggle="modal"  data-target="#addDebt">
                                                               <i class="material-icons" title="Tạo phiếu nợ">&#xE147;</i>add debt
                                                            </a>
                                                            <a href="edit-debtor?debtorId=${debtor.debtorId}" class="btn btn-warning" data-toggle="modal" data-target="#update-debtor">
                                                               <i class="material-icons" data-toggle="tooltip" title="Edit">&#xe3c9;</i>edit
                                                            </a>

                                                        </td>
                                                </tr>                                              
                                            </c:forEach>                                                                                   
                                        </tbody>

                                    </table>
                                </section>

                            </div>
                        </div>

                    </div>
                </form>

            </main>

        </div>

        <!-- add debtor HTML -->

        <div id="addDeptor" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add-debtor" method="post" class="form">
                        <div class="modal-header">      
                            <h4 class="modal-title">Thêm người nợ</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">     
                            <div class="form-group">
                                <label>Tên</label>
                                <div class="form-group"><input placeholder="Input name" type="text" name="debtorName" required class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <div class="form-group"><input placeholder="Input address" type="text" name="debtorAddress" required class="form-control">

                                </div>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <div class="form-group"><input placeholder="Input phone number" type="text" name="phoneNumber" required class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <div class="form-group"><input placeholder="Input email" type="text" name="debtorEmail" required class="form-control">
                                </div>
                            </div>

                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- add debt HTML -->
        <div id="insert-debt" class="modal fade">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <form action="insertdebt?debtorId=${debtor.debtorId}" method="post" class="fom" id="form-add-debt">
                    
                        <div class="modal-header">      
                            <h4 class="modal-title">Thêm phiếu nợ</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">     
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3">
                                    <label class=""  type="text">
                                        <b>Ghi chú </b>
                                    </label>
                                </div>
                                
                                <div class="col-md-9">
                                    <textarea rows="8" class="form-control" name="description"></textarea>
                                </div>
                            </div>
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class=""><b>Loại nợ (*)</b></label></div>
                                <div class="col-md-9"  style="display: flex">   
                                    
                                        <input type="radio" class="btn-check" name="type" id="type-false" value="false" autocomplete="off" >
                                        <label class="btn btn-outline-success" for="type-false">Khoản vay</label>

                                        <input type="radio" class="btn-check" name="type" id="type-true" value="true" autocomplete="off" checked>
                                        <label class="btn btn-outline-danger" for="type-true">Khoản nợ</label>                                       
                                    
                                </div>                            
                            </div>

                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class="" ><b>Số tiền (*)</b></label></div>
                                <div class="col-md-9">
                                    <input placeholder="Input number" type="text" name="debtValue" required class="form-control">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                                <input type="submit" class="btn btn-success" value="Thêm khoản nợ">
                            </div>
                        </div>
                    </form>
                </div>
                
            </div>
        </div>

        <!--update debtor-->
        <div id="update-debtor" class="modal fade">
            <div class="modal-dialog">                
                <div class="modal-content">
                <form action="edit-debtor?debtorId=${sessionScope.debtorToEdit.debtorId}" method="post" class="fom" id="form-edit-debtor">
                    <div class="modal-header">      
                        <h4 class="modal-title">Cập nhật thông tin người nợ</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">     
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Tên </b></label></div>

                            <div class="col-md-9">
                                <input id="edit-name" class="form-control" placeholder="Input name" type="text" name="newDebtorName" value="" >
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Địa chỉ </b></label></div>

                            <div class="col-md-9">
                                <input id="edit-address" class="form-control" placeholder="Input Address" type="text" name="newDebtorAddress" value="" >
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Số điện thoại </b></label></div>

                            <div class="col-md-9">
                                <input id="edit-phone" class="form-control" placeholder="Input Phone " type="text" name="newPhoneNumber" value="">
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Số điện thoại </b></label></div>
                            <div class="col-md-9">
                                <input id="edit-email" class="form-control" placeholder="Input Email" type="text" name="newDebtorEmail" value="">
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.errorAddDebt ne null}">
                        <h4>${sessionScope.errorAddDebt}</h4>
                    </c:if>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                        <input type="submit" class="btn btn-success" value="Lưu thay đổi">
                    </div>

                    </form>
                </div>
            
        </div>
                    
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
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script>
                                                        function addDebt(debtorId){
                                                            var id = parseInt(debtorId);
                                                            var oldAc = document.getElementById("form-add-debt");
                                                            
                                                            oldAc.action = "insertdebt?debtorId="+id;
                                                            
                                                        }
                                                        
                                                        function editDebtor(debtorId, debtorName, debtorAddress, debtorPhoneNumber, debtorEmail){
                                                            var id = parseInt(debtorId);
                                                            var oldAc = document.getElementById("form-edit-debtor");
                                                            oldAc.action = "edit-debtor?debtorId="+id;
                                                            
                                                            document.getElementById("edit-name").value = debtorName;
                                                            document.getElementById("edit-address").value = debtorAddress;
                                                            document.getElementById("edit-phone").value = debtorPhoneNumber;
                                                            document.getElementById("edit-email").value = debtorEmail;
                                                            
                                                            console.log(debtorName);
                                                        }
                                                        
                                                        function filter() {
                                                            var idFrom = document.getElementById("idFrom-filter").value;
                                                            var idTo = document.getElementById("idTo-filter").value;
                                                            var name = document.getElementById("name-filter").value;
                                                            var address = document.getElementById("address-filter").value;
                                                            var phoneNumber = document.getElementById("phone-filter").value;
                                                            var email = document.getElementById("email-filter").value;
                                                            var totalDebtFrom = document.getElementById("totalDebtFrom-filter").value;
                                                            var totalDebtTo = document.getElementById("totalDebtTo-filter").value;

                                                            var createDateFrom = document.getElementById("createDateFrom-filter").value;
                                                            var createDateTo = document.getElementById("createDateTo-filter").value;

                                                            var updateDateFrom = document.getElementById("updateDateFrom-filter").value;
                                                            var updateDateTo = document.getElementById("updateDateTo-filter").value;

                                                            var index = document.getElementById("page-index").value;
                                                            
                                                            var intIndex = parseInt(index);
                                                            var maxPage = document.getElementById("max-page").value;
                                                            var max = parseInt(maxPage);
                                                            
                                                            if((intIndex < 1) || (intIndex > max)){
                                                                //set index = 1
                                                                index = 1;
                                                                //alert 
                                                                
                                                            }
                                                            
                                                            var size = document.getElementById("page-size").value;
                                                            $.ajax({
                                                                url: "/ProjectV1/search-debtor",
                                                                type: "get",
                                                                data: {
                                                                    filterIdFrom: idFrom,
                                                                    filterIdTo: idTo,
                                                                    filterName: name,
                                                                    filterAddress: address,
                                                                    filterPhoneNumber: phoneNumber,
                                                                    filterEmail: email,
                                                                    filterTotalDebtFrom: totalDebtFrom,
                                                                    filterTotalDebtTo: totalDebtTo,
                                                                    filterCreateDateFrom: createDateFrom,
                                                                    filterCreateDateTo: createDateTo,
                                                                    filterUpdateDateFrom: updateDateFrom,
                                                                    filterUpdateDateTo: updateDateTo,

                                                                    pageIndex: index,
                                                                    pageSize: size
                                                                },
                                                                success: function (data) {
                                                                    var row = document.getElementById("content-table");
                                                                    row.innerHTML = data;
//                                                                  
                                                                    var maxPageNumber = document.getElementById("max-page-count").value;
                                                                    document.getElementById("max-page").value = maxPageNumber;
                                                                    document.getElementById("max-page").innerHTML = "/"+maxPageNumber;
                                                                    
                                                                }
                                                            });
                                                        }

                                                        function clearFilter() {
                                                            document.getElementById("idFrom-filter").value = "";
                                                            document.getElementById("idTo-filter").value = "";
                                                            document.getElementById("name-filter").value = "";

                                                            document.getElementById("address-filter").value = "";
                                                            document.getElementById("phone-filter").value = "";
                                                            document.getElementById("email-filter").value = "";
                                                            document.getElementById("totalDebtFrom-filter").value = "";
                                                            document.getElementById("totalDebtTo-filter").value = "";

                                                            document.getElementById("createDateFrom-filter").value = "";
                                                            document.getElementById("createDateTo-filter").value = "";

                                                            document.getElementById("updateDateFrom-filter").value = "";
                                                            document.getElementById("updateDateTo-filter").value = "";
                                                        }

                                                        function moveBefore() {
                                                            var pageIndex = document.getElementById("page-index").value;
                                                            if(pageIndex === ""){
                                                                pageIndex = 1;
                                                            }
                                                            var currentPageIndex = parseInt(pageIndex);

                                                            if (currentPageIndex > 1) {
                                                                document.getElementById("page-index").value = currentPageIndex - 1;
                                                                filter();
                                                            }
                                                        }

                                                        function moveNext() {
                                                            var pageIndex = document.getElementById("page-index").value;
                                                            if(pageIndex === ""){
                                                                pageIndex = 1;
                                                            }
                                                            var currentPageIndex = parseInt(pageIndex);
                                                            var maxPage = document.getElementById("max-page").value;
                                                            var max = parseInt(maxPage);
                                                            
                                                            if (currentPageIndex < max) {
                                                                document.getElementById("page-index").value = currentPageIndex + 1;
                                                                filter();
                                                            }
                                                        }
                                                        
                                                        window.addEventListener("load", function () {
                                                            filter(); // Gọi hàm filter() khi trang được tải lại
                                                        });
                                                        
    </script>

</body>



</html>