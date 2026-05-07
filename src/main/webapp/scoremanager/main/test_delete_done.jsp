<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績削除完了</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績削除完了</h2>

            <div class="px-4">
                <p>以下の成績を削除しました。</p>

                <table class="table table-bordered w-50">
                    <tr>
                        <th>学生番号</th>
                        <td>${test.student.no}</td>
                    </tr>
                    <tr>
                        <th>氏名</th>
                        <td>${test.student.name}</td>
                    </tr>
                    <tr>
                        <th>科目</th>
                        <td>${test.subject.name}</td>
                    </tr>
                    <tr>
                        <th>回数</th>
                        <td>${test.no}</td>
                    </tr>
                    <tr>
                        <th>点数</th>
                        <td>${test.point}</td>
                    </tr>
                </table>

                <a href="TestList.action" class="btn btn-secondary mt-2">戻る</a>
            </div>

        </section>
    </c:param>
</c:import>
