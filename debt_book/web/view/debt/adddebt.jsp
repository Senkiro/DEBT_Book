<%-- 
    Document   : adddebt
    Created on : Feb 17, 2023, 11:58:42 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="${pageContext.request.contextPath}/view/ASSESTS/IMG/icon.png" type="image/x-icon">
        <title>Thêm phiếu nợ</title>
        
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        
    </head>

    <body>
        
<!--        <h1>Thêm phiếu nợ</h1>
        <div class="card-body ">
            <div class="">
                <form action="insertdebt" method="post" class="fom">
                    <div class="position-relative row form-group">
                        <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Ghi chú </b></label></div>
                        
                        <div class="col-md-9"><textarea  rows="8" placeholder="" class="form-control" name="description"></textarea></div>
                    </div>
                    <div class="position-relative row form-group">
                        <div class="form-label-horizontal col-md-3"><label class=""><b>Loại nợ (*)</b></label></div>
                        <div class="col-md-9"  style="display: flex">
                            -<input type="radio" name="type" checked value="true"
                                    style="
                                    margin-right: 5px;
                                    margin-bottom: 5px;
                                    display: flex">                                        
                            </input>
                            <div>
                                <input type="radio" class="btn-check" name="type" id="type-false" value="false" autocomplete="off" >
                                <label class="btn btn-outline-success" for="success-outlined">Khoản vay</label>

                                <input type="radio" class="btn-check" name="type" id="type-true" value="true" autocomplete="off" checked>
                                <label class="btn btn-outline-danger" for="danger-outlined">Khoản nợ</label>
                            </div>
                            
                            +<input type="radio" name="type" value="false" style="
                                   margin-right: 5px;
                                   margin-bottom: 5px;
                                   display: flex">
                            </input>
                            </div>
                            </div>
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
                    <div style="display: flex;
                         justify-content: center;
                         align-items: center;"><button type="submit" class="button"
                              class="mr-1 btn btn-success"><i class="fa fa-plus"></i> Thêm</button></div>
                </form> comment  </div>
        </div>-->

        <div class="modal-content">
                    <!--<form action="insertdebt?debtorId=${debtor.debtorId}" method="post" class="fom">-->
            <form action="insertdebt?debtorId=${sessionScope.debtorId}" method="post" class="fom">
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
                                <!--<input type="text" name="debtValue" required>-->
                                <div class="col-md-9">
                                    <!--<textarea rows="8" class="form-control" name="description"></textarea>-->
                                    <c:if test="${requestScope.debtUpdate eq null}">                                       
                                        <textarea rows="8" class="form-control" name="description"></textarea>
                                        <input type="hidden" name="status" value="false">
                                    </c:if>
                                    <c:if test="${requestScope.debtUpdate ne null}">                                        
                                        <textarea rows="8" class="form-control" disabled="true"
                                                  >Thanh toán cho bản ghi số ${requestScope.debtUpdate.debtId}</textarea>
                                        <input type="hidden" name="description" value="Thanh toán cho bản ghi số ${requestScope.debtUpdate.debtId}">
                                        <input type="hidden" name="status" value="${requestScope.debtUpdate.status}">
                                    </c:if>
                                </div>
                            </div>
                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class=""><b>Loại nợ (*)</b></label></div>
                                <div class="col-md-9"  style="display: flex">   
                                    <c:if test="${requestScope.debtUpdate eq null}">
                                        <input type="radio" class="btn-check" name="type" id="type-false" value="false" autocomplete="off" >
                                        <label class="btn btn-outline-success" for="type-false">Khoản vay</label>

                                        <input type="radio" class="btn-check" name="type" id="type-true" value="true" autocomplete="off" checked>
                                        <label class="btn btn-outline-danger" for="type-true">Khoản nợ</label>                                       
                                    </c:if>
                                    <c:if test="${requestScope.debtUpdate ne null}">
                                        
                                        <input type="hidden" name="type" value="${requestScope.debtUpdate.type}">
                                        
                                        <input type="radio" class="btn-check" id="type-false" 
                                               <c:if test="${!requestScope.debtUpdate.type}">checked</c:if> autocomplete="off" disabled>
                                        <label class="btn btn-outline-success" for="type-false">Khoản vay</label>

                                        <input type="radio" class="btn-check" id="type-true" 
                                               <c:if test="${requestScope.debtUpdate.type}">checked</c:if> autocomplete="off" disabled>
                                        <label class="btn btn-outline-danger" for="type-true">Khoản nợ</label>
                                        
                                    </c:if>
                                </div>                            
                            </div>

                            <div class="position-relative row form-group">
                                <div class="form-label-horizontal col-md-3"><label class="" ><b>Số tiền (*)</b></label></div>
                                <div class="col-md-9">
                                    <input value="${requestScope.debtUpdate.debtValue}" placeholder="Input number" type="text" name="debtValue" required class="form-control">
                                    
                                </div>
                            </div>
                            

                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${requestScope.errorAddDebt ne null}">
                                <h4>${requestScope.errorAddDebt}</h4>
                </c:if>

    </body>
</html>
