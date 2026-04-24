<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績削除</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4">
                成績削除
            </h2>

            <div class="px-4 my-3">

                <p class="mb-4">
                    以下の成績を削除してよろしいですか？
                </p>

                <table class="table table-bordered w-50">
                    <tr>
                        <th class="bg-light">学生番号</th>
                        <td>${test.studentNo}</td>
                    </tr>
                    <tr>
                        <th class="bg-light">氏名</th>
                        <td>${test.studentName}</td>
                    </tr>
                    <tr>
                        <th class="bg-light">科目</th>
                        <td>${test.subjectName}</td>
                    </tr>
                    <tr>
                        <th class="bg-light">回数</th>
                        <td>${test.no}</td>
                    </tr>
                    <tr>
                        <th class="bg-light">点数</th>
                        <td>${test.point}</td>
                    </tr>
                </table>

                <form action="TestDeleteAction" method="post" class="mt-4">
                    <input type="hidden" name="student_no" value="${test.studentNo}">
                    <input type="hidden" name="subject_cd" value="${test.subjectCd}">
                    <input type="hidden" name="no" value="${test.no}">

                    <button class="btn btn-danger px-4">削除</button>
                    <a href="TestList" class="btn btn-secondary ms-3">戻る</a>
                </form>

            </div>

        </section>
    </c:param>
</c:import>
