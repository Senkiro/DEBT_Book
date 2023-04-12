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
        <title>Danh sách khoản nợ</title>

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
                            <a href="debtor">
                                <span class="las la-clipboard-list" ></span>
                                <small>Sổ ghi nợ</small>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="active">
                                <span class="las la-address-book" ></span>
                                
                                <small>Danh sách khoản nợ</small>
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
                <form action="debt" method="POST">

                    <div class="page-content">

                        <div class="records table-responsive">

                            <div class="record-header">
                                <div class="add">
                                    <!--<a href="#addDeptor" class="btn btn-success" data-toggle="modal" ><span>Thêm người nợ</span></a>-->
                                    <a href="#addDebt" data-toggle="modal" class="btn btn-success"><i class="material-icons" title="Tạo phiếu nợ">&#xE147;</i>Thêm khoản nợ</a>
                                    
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
                                    <select  onchange="filter()" id="page-size" name="pageSize">
                                        <option value="5">5 records</option>
                                        <option value="10" selected>10 records</option>
                                        <option value="25">25 records</option>
                                        <option value="50">50 records</option>
                                    </select>

                                </div>
                            </div>

                            <div>
                                <section class="table_body ">
                                    <table id="debt-list" class="table table-striped table-hover table-responsive" width="98%">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th>Ghi chú</th>
                                                <th>Loại nợ</td>
                                                <th>Số tiền</th>
                                                <th>Thời gian tạo</th>
                                                <th class="action">Action</th>
                                            </tr>
                                            <tr>
                                                <td>                                                  
                                                    <input oninput="filter()" class="form-control" type="number" id="idFrom-filter" name="filterIdFrom" placeholder="id from"> <br><!-- comment -->
                                                    <input oninput="filter()" class="form-control" type="number" id="idTo-filter" name="filterIdTo" placeholder="id to">
                                                </td>                                              
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="des-filter" type="text" name="filterDescription" placeholder="Ghi chú">
                                                </td>
                                                
                                                <td>
                                                    <select class="form-control " onchange="filter()" id="debt-type" name="filterType">
                                                        <option value="" selected>Tất cả</option>
                                                        <option value="true">Khoản nợ</option>
                                                        <option value="false">Khoản vay</option>
                                                    </select>
                                                </td>
                                                
                                                <td>                                                  
                                                    <input oninput="filter()" class="form-control" type="number" id="totalDebtFrom-filter" name="filterValueFrom" placeholder="Giá trị"> <br>
                                                    <input oninput="filter()" class="form-control" type="number" id="totalDebtTo-filter" name="filterValueTo" placeholder="Giá trị">
                                                </td>
                                                <td>
                                                    <input oninput="filter()" class="form-control" id="createDateFrom-filter" type="date" name="filterCreateDateFrom" placeholder=""> <br>
                                                    <input oninput="filter()" class="form-control" id="createDateTo-filter" type="date" name="filterCreateDateTo" placeholder="">
                                                </td>
                                                
                                                <td class="action">
                                                    <button type="button" onclick="clearFilter(); filter()" class="btn container-md btn-danger btn-lg" id="clear-filter">Xóa bộ lọc</button>
                                                </td>
                                            </tr>
                                        </thead>
                                        <tbody id="content-table">

                                        <c:forEach items="${sessionScope.debtList}" var="debt">
                                            <tr>
                                                    <td >${debt.debtId}</td>
                                                    
                                                    <td >${debt.description}</td>
                                                    <td >${debt.type}</td>
                                                    <td >
                                                        <strong class="debt">
                                                            <fmt:formatNumber  value="${debt.debtValue}" type="currency" currencyCode="VND"/>
                                                        </strong>
                                                    </td>
                                                    <td >${debt.createDate}</td>
                                                <td>

                                                    <div style="display: flex" class="action">
                                                        
                                                        <a href="#" onclick="showInfor()" class="btn btn-info"  data-toggle="modal"  data-target="#info-model">
                                                            <i class="material-icons d-block" title="Edit">&#xe88f;</i>
                                                            Chi tiết
                                                        </a> <br>
                                                        
                                                        <c:if test="${debt.status}">
                                                            <a href="insertdebt?debtorId=${debt.debtorId}&debtId=${debt.debtId}" data-toggle="modal" data-target="#pay-off-modal"
                                                                    class="mr-1 btn btn-danger disabled">
                                                                    <i class="fa fa-minus"> Thanh toán </i>
                                                            </a>
                                                        </c:if>                                                        
                                                        <c:if test="${!debt.status}">
                                                            <a href="#" onclick="payOff()" 
                                                               data-toggle="modal" data-target="#pay-off-modal"
                                                                    class="mr-1 btn btn-danger">
                                                                    <i class="fa fa-minus"> Thanh toán </i>
                                                            </a>
                                                        </c:if>
                                                    </div>
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
        
        <!--info model-->
        <div id="info-model" class="modal fade">
            <div class="modal-dialog">                
                <div class="modal-content">
                <form action="edit-debtor?debtorId=${sessionScope.debtorToEdit.debtorId}" method="post" class="fom" id="form-edit-debtor">
                    <div class="modal-header">      
                        <h4 class="modal-title">Thông tin khoản nợ</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">     
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Ghi chú</b></label></div>

                            <div class="col-md-9">
                                <input id="infor-description" disabled class="form-control" placeholder="" type="text" name="newDebtorName" value="" >
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Loại nợ</b></label></div>

                            <div class="col-md-9">
                                <input id="infor-type" disabled class="form-control" placeholder="" type="text" name="type" value="" >
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Số tiền</b></label></div>

                            <div class="col-md-9">
                                <input id="infor-debtValue" disabled class="form-control" placeholder="" type="text" name="debtValue" value="">
                            </div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Thời gian tạo</b></label></div>
                            <div class="col-md-9">
                                <input id="infor-createDate" disabled class="form-control" placeholder="" type="text" name="createDate" value="">
                            </div>
                        </div>
                    </div>
                    
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    </div>

                    </form>
                </div>
            
        </div>
                    
        </div>
        
        <!--pay-off model-->
        <div id="pay-off-modal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="insertdebt?debtorId=${sessionScope.debtorId}" method="post" class="fom" id="form-insert-debt">
                        <div class="modal-header">      
                            <h4 class="modal-title">Thanh toán</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">     
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Ghi chú </b></label></div>
                                <!--<input type="text" name="debtValue" required>-->
                                <div class="col-md-9">
                                    <textarea  rows="8" placeholder="" class="form-control" name="description" id="pay-des-text" disabled></textarea>
                                    <input id="pay-des-input" type="hidden" name="description" value="">
                                    <input id="pay-status-input" type="hidden" name="status" value="">
                                </div>
                            </div>
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class=""><b>Loại nợ (*)</b></label></div>
                                <div class="col-md-9"  style="display: flex">
                                    
                                    <input type="radio" class="btn-check" name="typeDebt" id="pay-type-false" value="false" autocomplete="off" disabled>
                                    <label class="btn btn-outline-success" for="pay-type-false">Khoản vay</label>

                                    <input type="radio" class="btn-check" name="typeDebt" id="pay-type-true" value="true" autocomplete="off" disabled>
                                    <label class="btn btn-outline-danger" for="pay-type-true">Khoản nợ</label>
                                    <input id="pay-type-input" type="hidden" name="type" value="">
                                </div>                            
                            </div>

                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class="" ><b>Số tiền (*)</b></label></div>
                                <div class="col-md-9">
                                    <input id="pay-debt-value" placeholder="Input number" type="text" name="debtValue" required class="form-control">

                                </div>
                            </div>

                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                                <input type="submit" class="btn btn-success" value="Thanh toán">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- add debt HTML -->
        <div id="addDebt" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="insertdebt?debtorId=${sessionScope.debtorId}" method="post" class="fom" >
                        <div class="modal-header">      
                            <h4 class="modal-title">Thêm phiếu nợ</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">     
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Ghi chú </b></label></div>
                                <!--<input type="text" name="debtValue" required>-->
                                <div class="col-md-9"><textarea  rows="8" placeholder="" class="form-control" name="description"></textarea></div>
                            </div>
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class=""><b>Loại nợ (*)</b></label></div>
                                <div class="col-md-9"  style="display: flex">
<!--                                    <input type="radio" name="type" checked value="true"
                                           style="
                                           margin-right: 5px;
                                           margin-bottom: 5px;
                                           display: flex">khoản nợ
                                    </input>
                                    <input type="radio" name="type" value="false" style="
                                           margin-left: 10px;
                                           margin-right: 5px;
                                           margin-bottom: 5px;
                                           display: flex">khoản vay
                                    </input>-->
                                    
                                    <input type="radio" class="btn-check" name="type" id="type-false" value="false" autocomplete="off" >
                                    <label class="btn btn-outline-success" for="type-false">Khoản vay</label>

                                    <input type="radio" class="btn-check" name="type" id="type-true" value="true" autocomplete="off" checked>
                                    <label class="btn btn-outline-danger" for="type-true">Khoản nợ</label>
                                </div>                            
                            </div>

                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class="" ><b>Số tiền (*)</b></label></div>
                                <div class="col-md-9"><input placeholder="Input number" type="text" name="debtValue" required class="form-control">

                                </div>
                            </div>
                            <c:if test="${sessionScope.errorAddDebt ne null}">
                                <h4>${sessionScope.errorAddDebt}</h4>
                            </c:if>

                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>

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
                        
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>   
    <script>
//                                                        function maxPage(){
//                                                            document.getElementById("max-page").value = "10";
//                                                        }
                                                        
                                                        function showInfor(description, typeDebt, debtValue, createDate){
                                                            
                                                            console.log(description);
                                                            
                                                            document.getElementById("infor-description").value = description;
                                                            document.getElementById("infor-type").value = typeDebt;
                                                            document.getElementById("infor-debtValue").value = debtValue;
                                                            document.getElementById("infor-createDate").value = createDate;
                                                            
                                                        }
                                                        
                                                        function payOff(debtorId, description, debtValue, type, status){
                                                            var id = parseInt(debtorId);
                                                            var oldAc = document.getElementById("form-insert-debt");
                                                            oldAc.action = "insertdebt?debtorId="+id;
                                                            
                                                            document.getElementById("pay-des-text").value = description;
                                                            document.getElementById("pay-des-input").value = description;
                                                            document.getElementById("pay-status-input").value = status;
                                                            document.getElementById("pay-debt-value").value = debtValue;
                                                            document.getElementById("pay-type-input").value = type;
                                                            
                                                            if(type === "true"){
                                                                document.getElementById("pay-type-true").checked = true;
                                                            }else if(type === "false"){
                                                                document.getElementById("pay-type-false").checked = true;
                                                            }
                                                            
                                                        }
                                                        
                                                        function filter() {
                                                            var idFrom = document.getElementById("idFrom-filter").value;
                                                            var idTo = document.getElementById("idTo-filter").value;
                                                            
                                                            var desciption = document.getElementById("des-filter").value;
                                                            
                                                            var type = document.getElementById("debt-type").value;
                                                            
                                                            var valueFrom = document.getElementById("totalDebtFrom-filter").value;
                                                            var valueTo = document.getElementById("totalDebtTo-filter").value;
                                                            

                                                            var createDateFrom = document.getElementById("createDateFrom-filter").value;
                                                            var createDateTo = document.getElementById("createDateTo-filter").value;

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
                                                                url: "/ProjectV1/filter-debt",
                                                                type: "get",
                                                                data: {
                                                                    filterIdFrom: idFrom,
                                                                    filterIdTo: idTo,
                                                                    filterDescription: desciption,
                                                                    
                                                                    filterType: type,
                                                                    
                                                                    filterValueFrom: valueFrom,
                                                                    filterValueTo: valueTo,
                                                                    
                                                                    filterCreateDateFrom: createDateFrom,
                                                                    filterCreateDateTo: createDateTo,
                                                                    

                                                                    pageIndex: index,
                                                                    pageSize: size
                                                                },
                                                                success: function (data) {
                                                                    var row = document.getElementById("content-table");
                                                                    row.innerHTML = data;
                                                                    
                                                                    var maxPageNumber = document.getElementById("max-pageDebt-count").value;
                                                                    document.getElementById("max-page").value = maxPageNumber;
                                                                    document.getElementById("max-page").innerHTML = "/"+maxPageNumber;
                                                                }
                                                            });
                                                        }

                                                        function clearFilter() {
                                                            document.getElementById("idFrom-filter").value = "";
                                                            document.getElementById("idTo-filter").value = "";
                                                            
                                                            document.getElementById("des-filter").value = "";
                                                            
                                                            document.getElementById("totalDebtFrom-filter").value = "";
                                                            document.getElementById("totalDebtTo-filter").value = "";

                                                            document.getElementById("createDateFrom-filter").value = "";
                                                            document.getElementById("createDateTo-filter").value = "";
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