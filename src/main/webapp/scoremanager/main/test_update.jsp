<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
<c:param name="title">成績変更</c:param>
<c:param name="scripts"></c:param>
 
    <c:param name="content">
<section class="me-4">
 
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績変更</h2>
 
            <form action="TestUpdateExecute.action" method="post" class="px-4">
 
                <div class="mb-3">
<label class="form-label">学生番号</label>
<input type="text" class="form-control" name="student_no"
                           value="${test.student.no}" readonly>
</div>
 
                <div class="mb-3">
<label class="form-label">氏名</label>
<input type="text" class="form-control"
                           value="${test.student.name}" readonly>
</div>
 
                <div class="mb-3">
<label class="form-label">科目</label>
<input type="hidden" name="subject_cd" value="${test.subject.cd}">
<input type="text" class="form-control"
                           value="${test.subject.name}" readonly>
</div>
 
                <div class="mb-3">
<label class="form-label">回数</label>
<input type="number" class="form-control" name="no"
                           value="${test.no}" readonly>
</div>
 
                <div class="mb-3">
<label class="form-label">点数</label>
<input type="number" class="form-control" name="point"
                           value="${test.point}" min="0" max="100">
</div>
 
                <button class="btn btn-primary">変更</button>
<a href="TestList.action" class="ms-3">戻る</a>
 
            </form>
 
        </section>
</c:param>
</c:import>