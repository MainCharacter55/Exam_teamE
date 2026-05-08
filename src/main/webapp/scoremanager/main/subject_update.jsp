<%-- subject_update.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">

        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <!-- エラー表示 -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form action="SubjectUpdate.action" method="post">
                <!-- 科目コード（必ず送る） -->
                <input type="hidden" name="cd" value="${cd}">

                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <p>${cd}</p>
                </div>

                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input class="form-control" type="text" name="name" value="${name}" required>
                </div>

                <button class="btn btn-primary" type="submit">変更</button>
            </form>

            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>