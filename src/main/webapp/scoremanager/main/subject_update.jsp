<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>

<h2>科目変更</h2>

<form action="SubjectUpdate.action" method="post">

    <div>
        <label>科目コード</label><br>
        <input type="text" name="cd" value="${cd}" readonly>
    </div>

    <br>

    <div>
        <label>科目名</label><br>
        <input type="text" name="name" value="${name}" maxlength="20" required>
    </div>

    <br>

    <div>
        <input type="submit" value="変更">
    </div>

</form>

<br>

<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>