<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">CSV一括登録完了</h2>

            <div class="px-4">
                <p class="mb-3">${successCount}件の学生情報を登録しました。</p>

                <c:if test="${not empty errors}">
                    <div class="alert alert-warning">
                        <strong>以下の行でエラーが発生しました：</strong>
                        <ul class="mb-0 mt-2">
                            <c:forEach var="err" items="${errors}">
                                <li>${err}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <a href="StudentList.action" class="btn btn-secondary mt-2">学生一覧へ</a>
                <a href="StudentCreate.action" class="btn btn-outline-secondary mt-2 ms-2">続けて登録</a>
            </div>
        </section>
    </c:param>
</c:import>
