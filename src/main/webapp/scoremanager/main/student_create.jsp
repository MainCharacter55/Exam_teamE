<%-- student_create.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

            <div class="row px-3 g-4">

                <%-- 左カラム: 手動登録 --%>
                <div class="col-6">
                    <h3 class="h5 mb-3">手動登録</h3>
                    <form action="StudentCreateExecute.action" method="post">
                        <div class="mb-3">
                            <label class="form-label" for="student-ent-year-input">入学年度</label>
                            <select class="form-select" id="student-ent-year-input" name="ent_year" required>
                                <option value="">選択してください</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label" for="student-no-input">学生番号</label>
                            <input class="form-control" type="text" id="student-no-input" name="no"
                                   placeholder="学生番号を入力してください" value="${no}" maxlength="10" required />
                            <c:if test="${!empty errors.get('no')}">
                                <div class="text-danger small">${errors.get("no")}</div>
                            </c:if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label" for="student-name-input">氏名</label>
                            <input class="form-control" type="text" id="student-name-input" name="name"
                                   placeholder="氏名を入力してください" value="${name}" maxlength="10" required />
                        </div>

                        <div class="mb-3">
                            <label class="form-label" for="student-class-num-input">クラス</label>
                            <select class="form-select" id="student-class-num-input" name="class_num" required>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mt-4">
                            <button class="btn btn-secondary" type="submit" style="width: 125px;">登録して完了</button>
                        </div>
                    </form>

                    <div class="mt-3">
                        <a href="StudentList.action">戻る</a>
                    </div>
                </div>

                <%-- 縦区切り線 --%>
                <div class="col-auto">
                    <div class="vr h-100"></div>
                </div>

                <%-- 右カラム: CSV一括登録 --%>
                <div class="col">
                    <h3 class="h5 mb-3">CSVで一括登録</h3>
                    <p class="text-muted small mb-3">CSV形式: 学生番号,氏名,入学年度,クラス（1行目はヘッダー行）</p>
                    <form action="StudentCsvRegist.action" method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label class="form-label">CSVファイル</label>
                            <input type="file" class="form-control" name="csv_file" accept=".csv" required>
                        </div>
                        <button class="btn btn-secondary" type="submit">CSVで登録</button>
                    </form>
                </div>

            </div>
        </section>
    </c:param>
</c:import>