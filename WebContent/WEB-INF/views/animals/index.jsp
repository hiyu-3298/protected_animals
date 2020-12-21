<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>動物　一覧</h2>
        <table id="animals_list">
            <tbody>
                <tr>
                    <th class="animals_name">名前</th>
                    <th class="animals_date">日付</th>
                    <th class="animals_sex">性別</th>
                    <th class="animals_action">操作</th>
                </tr>
                <c:forEach var="animals" items="${animals}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="animals_name"><c:out value="${animals.name}" /></td>
                        <td class="animals_date"><fmt:formatDate value='${animals.animals_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="animals_sex">${animals.sex}</td>
                        <td class="animals_action"><a href="<c:url value='/animals/show?id=${animals.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${animals_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((animals_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/animals/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/animals/new' />">新規日報の登録</a></p>

    </c:param>
</c:import>