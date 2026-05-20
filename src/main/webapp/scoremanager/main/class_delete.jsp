<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4">クラス削除</h2>

            <div class="px-4 my-3">
                <p class="mb-4">以下のクラスを削除してよろしいですか？</p>

                <table class="table table-bordered w-25">
                    <tr>
                        <th class="bg-light">クラス</th>
                        <td>${class_num}</td>
                    </tr>
                </table>

                <form action="ClassDeleteExecute.action" method="post" class="mt-4">
                    <input type="hidden" name="class_num" value="${class_num}">
                    <button class="btn btn-danger px-4">削除</button>
                    <a href="ClassList.action" class="btn btn-secondary ms-3">戻る</a>
                </form>
            </div>
        </section>
    </c:param>
</c:import>
