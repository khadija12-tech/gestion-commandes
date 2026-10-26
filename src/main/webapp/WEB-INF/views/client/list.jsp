<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Clients</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2>Liste des clients</h2>
  <a href="${pageContext.request.contextPath}/clients?action=new">+ Nouveau client</a>
  <br><br>
  <table border="1" cellpadding="5">
    <tr>
      <th>ID</th><th>Nom</th><th>Prénom</th><th>Email</th><th>Téléphone</th><th>Actions</th>
    </tr>
    <c:forEach var="cl" items="${clients}">
      <tr>
        <td>${cl.id}</td>
        <td><c:out value="${cl.nom}"/></td>
        <td><c:out value="${cl.prenom}"/></td>
        <td><c:out value="${cl.email}"/></td>
        <td><c:out value="${cl.telephone}"/></td>
        <td>
          <a href="${pageContext.request.contextPath}/clients?action=edit&id=${cl.id}">Modifier</a> |
          <a href="${pageContext.request.contextPath}/clients?action=delete&id=${cl.id}"
             onclick="return confirm('Supprimer ce client ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
    <c:if test="${empty clients}">
      <tr><td colspan="6">Aucun client.</td></tr>
    </c:if>
  </table>
</body>
</html>