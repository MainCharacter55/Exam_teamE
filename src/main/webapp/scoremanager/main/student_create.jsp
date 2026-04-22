<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma">学生情報登録</h2>
            <form action="StudentCreateExecute.action" method="post">
                <div class="row">
                    <div class="col-3">
                        <label class="form-label" for="student-ent-year-input">入学年度</label>
                        <select class="form-select" id="student-ent-year-input" name="ent_year">
                            <option value="0">選択してください</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <%-- 登録エラー時に選択していた値を保持 --%>
                                <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label" for="student-no-input">学生番号</label>
                        <input class="form-control" type="text" id="student-no-input" name="no" 
                               placeholder="学生番号を入力してください" value="${no}" maxlength="10" required />
                        <%-- エラーメッセージの表示 --%>
                        <div class="text-danger">${errors.get("no")}</div>
                    </div>

                    <div class="col-3">
                        <label class="form-label" for="student-name-input">氏名</label>
                        <input class="form-control" type="text" id="student-name-input" name="name" 
                               placeholder="氏名を入力してください" value="${name}" maxlength="10" required />
                    </div>

                    <div class="col-3">
                        <label class="form-label" for="student-class-num-input">クラス</label>
                        <select class="form-select" id="student-class-num-input" name="class_num">
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-12 mt-3">
                        <button class="btn btn-secondary" type="submit">登録</button>
                    </div>
                </div>
            </form>
            <div class="mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>