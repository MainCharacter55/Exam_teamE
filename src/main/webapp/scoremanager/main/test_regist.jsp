<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <form action="TestRegistSearch.action" method="post">
                <div class="row border mx-3 mb-3 py-3 align-items-center rounded">
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <option value="">--------</option>
                            <c:forEach var="subject" items="${subject_set}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select class="form-select" name="f4">
                            <option value="0">--------</option>
                            <option value="1" <c:if test="${f4 == 1}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${f4 == 2}">selected</c:if>>2</option>
                            <option value="3" <c:if test="${f4 == 3}">selected</c:if>>3</option>
                            <option value="4" <c:if test="${f4 == 4}">selected</c:if>>4</option>
                            <option value="5" <c:if test="${f4 == 5}">selected</c:if>>5</option>
                        </select>
                    </div>

                    <div class="col-2 text-center mt-4">
                        <button class="btn btn-secondary" type="submit">検索</button>
                    </div>
                </div>
            </form>

            <c:if test="${isSearchPerformed}">
                <c:choose>
                    <c:when test="${not empty regist_list}">
                        <div class="ms-3 mb-2">
                            科目：${subject_name}（${f4}回）
                        </div>

                        <form action="TestRegistExecute.action" method="post">
                            <input type="hidden" name="f1" value="${f1}">
                            <input type="hidden" name="f2" value="${f2}">
                            <input type="hidden" name="f3" value="${f3}">
                            <input type="hidden" name="f4" value="${f4}">

                            <table class="table table-bordered mx-3" style="width: calc(100% - 2rem);">
                                <thead class="table-light">
                                    <tr>
                                        <th>入学年度</th>
                                        <th>クラス</th>
                                        <th>学生番号</th>
                                        <th>氏名</th>
                                        <th>点数</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="test" items="${regist_list}">
                                        <tr>
                                            <td>${test.entYear}</td>
                                            <td>${test.classNum}</td>
                                            <td>
                                                <input type="hidden" name="student_no" value="${test.studentNo}">
                                                ${test.studentNo}
                                            </td>
                                            <td>${test.studentName}</td>
                                            <td>
                                                <input type="number" class="form-control" name="point"
                                                       value="${test.getPoint(f4)}" min="0" max="100">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <div class="mx-3 mt-2">
                                <button class="btn btn-primary" type="submit">登録して終了</button>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="mt-3 ms-3">成績情報が存在しませんでした</div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </section>
    </c:param>
</c:import>
