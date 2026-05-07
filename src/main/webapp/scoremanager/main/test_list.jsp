<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績一覧（学生）</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>

            <div class="px-4">

                <%-- 科目情報検索 --%>
                <form action="TestList.action" method="post" class="mb-3">
                    <div class="d-flex align-items-end gap-3 flex-wrap">
                        <div>
                            <label class="form-label">科目情報</label>
                        </div>
                        <div>
                            <label class="form-label">入学年度</label>
                            <select class="form-select" name="ent_year">
                                <option value="0">--------</option>
                                <c:forEach var="y" items="${ent_year_set}">
                                    <option value="${y}">${y}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label class="form-label">クラス</label>
                            <select class="form-select" name="class_num">
                                <option value="0">--------</option>
                                <c:forEach var="cn" items="${class_num_set}">
                                    <option value="${cn}">${cn}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label class="form-label">科目</label>
                            <select class="form-select" name="subject_cd">
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subject_set}">
                                    <option value="${sub.cd}">${sub.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <button class="btn btn-primary">検索</button>
                        </div>
                    </div>
                </form>

                <%-- 学生番号検索 --%>
                <form action="TestList.action" method="post" class="mb-4">
                    <div class="d-flex align-items-end gap-3">
                        <div>
                            <label class="form-label">学生情報</label>
                        </div>
                        <div>
                            <label class="form-label">学生番号</label>
                            <input type="text" class="form-control" name="student_no" value="${student_no}">
                        </div>
                        <div>
                            <button class="btn btn-primary">検索</button>
                        </div>
                    </div>
                </form>

                <%-- 検索結果 --%>
                <c:if test="${not empty scores}">
                    <p>氏名：${scores[0].studentName}（${scores[0].studentNo}）</p>
                    <table class="table table-bordered table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="score" items="${scores}">
                                <tr>
                                    <td>${score.subjectName}</td>
                                    <td>${score.subjectCd}</td>
                                    <td>${score.testNo}</td>
                                    <td>${score.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>

            </div>
        </section>
    </c:param>
</c:import>
