<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス登録</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger mx-4">${error}</div>
            </c:if>

            <form action="ClassCreateExecute.action" method="post" class="px-4">
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <input type="text" class="form-control w-25" name="class_num" value="${class_num}" required>
                </div>
                <button class="btn btn-primary">登録</button>
                <a href="ClassList.action" class="ms-3">戻る</a>
            </form>
        </section>
    </c:param>
</c:import>
