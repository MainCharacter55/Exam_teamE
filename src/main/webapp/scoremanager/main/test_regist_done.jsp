<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績登録完了</h2>

            <div class="px-4">
                <p>以下の成績を登録しました。</p>

                <table class="table table-bordered w-50">
                    <tr>
                        <th>科目</th>
                        <td>${subject.name}</td>
                    </tr>
                    <tr>
                        <th>回数</th>
                        <td>${no}</td>
                    </tr>
                    <tr>
                        <th>登録件数</th>
                        <td>${count} 件</td>
                    </tr>
                </table>

                <a href="TestRegist.action" class="btn btn-secondary mt-2">戻る</a>
                <a href="TestList.action" class="ms-3 mt-2">成績参照</a>
            </div>
        </section>
    </c:param>
</c:import>
