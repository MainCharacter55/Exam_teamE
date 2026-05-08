<%-- student_create_done.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録完了</h2>
            <p class="text-center" style="background-color:#66CC99">登録が完了しました。</p>
            <div class="mt-3">
            	<a href="StudentCreate.action">戻る</a>
                <a href="SubjectList.action">科目一覧に戻る</a>
            </div>
        </section>
    </c:param>
</c:import>