<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${manager != null}">
                <h2>${manager.name} の管理者情報詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>登録番号</th>
                            <td><c:out value="${manager.code}" /></td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${manager.name}" /></td>
                        </tr>
                        <tr>
                            <th>都道府県</th>
                            <td><c:out value="${manager.prefectures}" /></td>
                        </tr>
                        <tr>
                            <th>市町村</th>
                            <td><c:out value="${manager.municipalities}" /></td>
                        </tr>
                        <tr>
                            <th>電話番号</th>
                            <td><c:out value="${manager.phone}" /></td>
                        </tr><tr>
                            <th>メールアドレス</th>
                            <td><c:out value="${manager.email_address}" /></td>
                        </tr>
                        <tr>
                            <th>権限</th>
                            <td>
                                <c:choose>
                                    <c:when test="${manager.admin_flag == 1}">スーパー管理者</c:when>
                                    <c:otherwise>管理者</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${manager.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${manager.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/manager/edit?id=${manager.id}' />">この管理者情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/manager/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>