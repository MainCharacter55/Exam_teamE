<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス変更</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger mx-4">${error}</div>
            </c:if>

            <form action="ClassUpdate.action" method="post" class="px-4">
                <input type="hidden" name="class_num" value="${class_num}">

                <div class="mb-3">
                    <label class="form-label">現在のクラス</label>
                    <p class="form-control-plaintext">${class_num}</p>
                </div>

                <div class="mb-3">
                    <label class="form-label">新しいクラス</label>
                    <input type="text" class="form-control w-25" name="new_class_num" value="${new_class_num}" required>
                </div>

                <button class="btn btn-primary">変更</button>
                <a href="ClassList.action" class="ms-3">戻る</a>
            </form>
        </section>
    </c:param>
</c:import>
