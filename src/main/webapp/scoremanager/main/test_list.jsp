<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

            <%-- 科目情報検索 --%>
            <form action="TestList.action" method="get">
                <div class="border mx-3 mb-2 py-3 px-3 rounded">
                    <div class="row align-items-end g-2">
                        <div class="col-auto fw-bold align-self-center">科目情報</div>

                        <div class="col-2">
                            <label class="form-label">入学年度</label>
                            <select class="form-select" name="f1">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1 and searchType == 'subject'}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-2">
                            <label class="form-label">クラス</label>
                            <select class="form-select" name="f2">
                                <option value="">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == f2 and searchType == 'subject'}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-3">
                            <label class="form-label">科目</label>
                            <select class="form-select" name="f3">
                                <option value="">--------</option>
                                <c:forEach var="subject" items="${subject_set}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd == f3 and searchType == 'subject'}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-auto">
                            <button class="btn btn-secondary" type="submit">検索</button>
                        </div>
                    </div>
                </div>
            </form>

            <%-- 学生情報検索 --%>
            <form action="TestList.action" method="get">
                <div class="border mx-3 mb-3 py-3 px-3 rounded">
                    <div class="row align-items-end g-2">
                        <div class="col-auto fw-bold align-self-center">学生情報</div>

                        <div class="col-4">
                            <label class="form-label">学生番号</label>
                            <input type="text" class="form-control" name="student_no"
                                   placeholder="学生番号を入力してください"
                                   value="${searchType == 'student' ? student_no : ''}">
                        </div>

                        <div class="col-auto">
                            <button class="btn btn-secondary" type="submit">検索</button>
                        </div>
                    </div>
                </div>
            </form>

            <%-- 結果表示 --%>
            <c:choose>
                <c:when test="${isSearchPerformed}">
                    <c:choose>

                        <%-- 科目情報検索結果 --%>
                        <c:when test="${searchType == 'subject'}">
                            <c:choose>
                                <c:when test="${not empty tests}">
                                    <div class="ms-3 mb-2">科目：${subject_name}</div>
                                    <table class="table table-hover mx-3" style="width: calc(100% - 2rem);">
                                        <thead class="table-light">
                                            <tr>
                                                <th>入学年度</th>
                                                <th>クラス</th>
                                                <th>学生番号</th>
                                                <th>氏名</th>
                                                <th class="text-center">1回</th>
                                                <th class="text-center">2回</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="test" items="${tests}">
                                                <tr>
                                                    <td>${test.entYear}</td>
                                                    <td>${test.classNum}</td>
                                                    <td>${test.studentNo}</td>
                                                    <td>${test.studentName}</td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${not empty test.getPoint(1)}">
                                                                ${test.getPoint(1)}
                                                                <a href="TestUpdate.action?student_no=${test.studentNo}&subject_cd=${f3}&no=1" class="ms-1">変更</a>
                                                                <a href="TestDelete.action?student_no=${test.studentNo}&subject_cd=${f3}&no=1" class="ms-1 text-danger">削除</a>
                                                            </c:when>
                                                            <c:otherwise>－</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${not empty test.getPoint(2)}">
                                                                ${test.getPoint(2)}
                                                                <a href="TestUpdate.action?student_no=${test.studentNo}&subject_cd=${f3}&no=2" class="ms-1">変更</a>
                                                                <a href="TestDelete.action?student_no=${test.studentNo}&subject_cd=${f3}&no=2" class="ms-1 text-danger">削除</a>
                                                            </c:when>
                                                            <c:otherwise>－</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <div class="mt-3 ms-3">成績情報が存在しませんでした</div>
                                </c:otherwise>
                            </c:choose>
                        </c:when>

                        <%-- 学生情報検索結果 --%>
                        <c:when test="${searchType == 'student'}">
                            <c:choose>
                                <c:when test="${not empty scores}">
                                    <div class="ms-3 mb-2">学生番号：${student_no}</div>
                                    <table class="table table-hover mx-3" style="width: calc(100% - 2rem);">
                                        <thead class="table-light">
                                            <tr>
                                                <th>科目</th>
                                                <th class="text-center">回数</th>
                                                <th class="text-center">点数</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="score" items="${scores}">
                                                <tr>
                                                    <td>${score.subjectName}</td>
                                                    <td class="text-center">${score.testNo}</td>
                                                    <td class="text-center">${score.point}</td>
                                                    <td><a href="TestUpdate.action?student_no=${score.studentNo}&subject_cd=${score.subjectCd}&no=${score.testNo}">変更</a></td>
                                                    <td><a href="TestDelete.action?student_no=${score.studentNo}&subject_cd=${score.subjectCd}&no=${score.testNo}" class="text-danger">削除</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <div class="mt-3 ms-3">成績情報が存在しませんでした</div>
                                </c:otherwise>
                            </c:choose>
                        </c:when>

                    </c:choose>
                </c:when>

                <%-- 初期表示メッセージ --%>
                <c:otherwise>
                    <div class="mt-3 ms-3 text-primary">
                        科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
                    </div>
                </c:otherwise>
            </c:choose>

        </section>
    </c:param>
</c:import>
