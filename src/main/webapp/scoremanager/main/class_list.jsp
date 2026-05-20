<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス一覧</h2>

            <div class="my-2 text-end px-4">
                <a href="ClassCreate.action">新規登録</a>
            </div>

            <c:choose>
                <c:when test="${not empty classes}">
                    <table class="table table-hover mx-3" style="width: calc(100% - 2rem);">
                        <thead class="table-light">
                            <tr>
                                <th>クラス</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cn" items="${classes}">
                                <tr>
                                    <td>${cn}</td>
                                    <td><a href="ClassUpdate.action?class_num=${cn}">変更</a></td>
                                    <td><a href="ClassDelete.action?class_num=${cn}" class="text-danger">削除</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="mt-3 ms-3">クラス情報が存在しません。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
