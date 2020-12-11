<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>里親一覧</h2>
        <table id="parents_list">
            <tbody>
                <tr>
                    <th>登録番号</th>
                    <th>氏名</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="parents" items="${parents}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${parents.code}" /></td>
                        <td><c:out value="${parents.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${parents.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/parents/show?id=${parents.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${parents_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((parents_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/parents/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/parents/new' />">里親の登録</a></p>

    </c:param>
</c:import>