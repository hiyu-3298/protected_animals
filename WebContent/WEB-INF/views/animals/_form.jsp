<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="animals_date">日付</label><br />
<input type="date" name="animals_date" value="<fmt:formatDate value='${animals.animals_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">名前</label><br />
<c:out value="${sessionScope.login_manager.name}" />
<br /><br />

<label for="sex">性別</label><br />
<input type="text" name="sex" value="${animals.sex}" />
<br /><br />

<label for="age">年齢</label><br />
<input type="text" name="age" value="${animals.age}" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${animals.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>