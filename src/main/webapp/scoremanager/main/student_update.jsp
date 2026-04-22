<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma">学生情報変更</h2>
            <form action="StudentUpdateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <p>${student.entYear}</p>
                </div>
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input class="form-control" type="text" name="no" value="${student.no}" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input class="form-control" type="text" name="name" value="${student.name}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select class="form-select" name="class_num">
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" name="is_attend" id="is_attend_check" 
                           <c:if test="${student.attend}">checked</c:if>>
                    <label class="form-check-label" for="is_attend_check">在学中</label>
                </div>
                <button class="btn btn-primary" type="submit">変更</button>
            </form>
            <div class="mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>