<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            
            <form action="TestList.action" method="get">
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
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <option value="0">--------</option>
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
                        </select>
                    </div>
                    <div class="col-2 text-center mt-4">
                        <button class="btn btn-secondary" type="submit">表示</button>
                    </div>
                </div>
            </form>

            <c:if test="${not empty errors.filter}">
                <div class="text-danger mx-3 mb-3">${errors.filter}</div>
            </c:if>

            <c:if test="${isSearchPerformed}">
			    <c:choose>
			        <c:when test="${not empty tests}">
			            <%-- Item 1: Display subject and round in brackets --%>
			            <div class="ms-3 mb-3">
			                科目：${subject_name} （${selected_num}回）
			            </div>
			
			            <table class="table table-hover mt-3">
			                <thead>
			                    <tr>
			                        <th>入学年度</th><%-- Item 3 --%>
			                        <th>クラス</th><%-- Item 4 --%>
			                        <th>学生番号</th><%-- Item 5 --%>
			                        <th>氏名</th><%-- Item 6 --%>
			                        <th class="text-center">点数</th><%-- Item 7: Single score column --%>
			                    </tr>
			                </thead>
			                <tbody>
			                    <c:forEach var="test" items="${tests}">
			                        <tr>
			                            <td>${test.entYear}</td><%-- Item 8 --%>
			                            <td>${test.classNum}</td><%-- Item 9 --%>
			                            <td>${test.studentNo}</td><%-- Item 10 --%>
			                            <td>${test.studentName}</td><%-- Item 11 --%>
			                            <%-- Item 12: Show only the score for the selected round --%>
			                            <td class="text-center">
			                                ${test.getPoint(selected_num)}
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
			</c:if>
        </section>
    </c:param>
</c:import>