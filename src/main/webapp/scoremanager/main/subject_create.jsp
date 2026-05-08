<%-- subject_create.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            
            <c:if test="${!empty error}">
                <div class="text-danger mb-3">${error}</div>
            </c:if>

            <form action="SubjectCreateExecute.action" method="post">
			    <div class="mb-3">
			        <label class="form-label" for="subject-cd-input">科目コード</label>
			        
			        <%-- value="${cd}" を追加して入力を保持 --%>
			        <input class="form-control" type="text" id="subject-cd-input" name="cd" 
			               placeholder="科目コードを入力してください" value="${cd}" 
			               minlength="3" maxlength="3" required>
			               
			        <%-- errors.cd に修正して、JavaのMapからメッセージを取得 --%>
			        <c:if test="${!empty errors.get('cd')}">
			            <div class="text-danger small mt-1">${errors.get("cd")}</div>
			        </c:if>
			    </div>
			    
			    <div class="mb-3">
			        <label class="form-label" for="subject-name-input">科目名</label>
			        
			        <%-- value="${name}" を追加して入力を保持 --%>
			        <input class="form-control" type="text" id="subject-name-input" name="name"
			        	placeholder="科目名を入力してください" value="${name}" required>
			    </div>
			    
			    <button class="btn btn-primary" type="submit">登録</button>
			</form>
            
            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>