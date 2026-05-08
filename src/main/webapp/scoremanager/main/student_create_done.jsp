<%-- student_create_done.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報登録</h2>
            
            <%-- 緑色の完了メッセージバー --%>
            <div id="wrap_box">
            	<p class="text-center" style="background-color:#66CC99">登録が完了しました</p>
            </div>

            <%-- 戻るリンクと学生一覧リンク --%>
            <div class="mt-4 d-flex gap-3">
                <a href="StudentCreate.action">戻る</a>
                <a href="StudentList.action">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>