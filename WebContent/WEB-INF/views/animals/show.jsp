<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${animals != null}">
                <h2>動物　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>管理者</th>
                            <td><c:out value="${animals.manager.name}" /></td>
                        </tr>
                        <tr>
                            <th>名前</th>
                            <td>
                                <pre><c:out value="${animals.name}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>性別</th>
                            <td>
                                <pre><c:out value="${animals.sex}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>年齢</th>
                            <td>
                                <pre><c:out value="${animals.age}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${animals.animals_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${animals.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${animals.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${animals.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_manager.id == animals.manager.id}">
                    <p><a href="<c:url value="/animals/edit?id=${animals.id}" />">この動物情報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/animals/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>