<%-- 
    Document   : edit-debtor
    Created on : Mar 12, 2023, 12:12:27 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật thông tin người nợ</title>
    </head>
    <body>

        <c:if test="${sessionScope.debtorToEdit ne null}">
            <div class="modal-content">
                <form action="edit-debtor?debtorId=${sessionScope.debtorToEdit.debtorId}" method="post" class="fom">
                    <div class="modal-header">      
                        <h4 class="modal-title">Cập nhật thông tin người nợ</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">     
                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Tên </b></label></div>

                            <div class="col-md-9"><input class="form-control" placeholder="Input name" type="text" name="newDebtorName" value="${sessionScope.debtorToEdit.debtorName}" ></div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Địa chỉ </b></label></div>

                            <div class="col-md-9"><input class="form-control" placeholder="Input Address" type="text" name="newDebtorAddress" value="${sessionScope.debtorToEdit.debtorAddress}" ></div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Số điện thoại </b></label></div>

                            <div class="col-md-9"><input class="form-control" placeholder="Input Phone " type="text" name="newPhoneNumber" value="${sessionScope.debtorToEdit.phoneNumber}"></div>
                        </div>

                        <div class="position-relative row form-group">
                            <div class="form-label-horizontal col-md-3"><label class=""  type="text" name="description"><b>Số điện thoại </b></label></div>

                            <div class="col-md-9"><input class="form-control" placeholder="Input Email" type="text" name="newDebtorEmail" value="${sessionScope.debtorToEdit.debtorEmail}"></div>
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

    </c:if>                


</body>
</html>
