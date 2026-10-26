<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Commandes</title></head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2>Liste des commandes</h2>
  <a href="${pageContext.request.contextPath}/commandes?action=new">+ Nouvelle commande</a>
  <br><br>
  <table border="1">
    <tr><th>N°</th><th>Date</th><th>Client</th><th>Actions</th></tr>
    <c:forEach var="cmd" items="${commandes}">
      <tr>
        <td>${cmd.id}</td>
        <td>${cmd.dateCommande}</td>
        <td><c:out value="${cmd.client.nom} ${cmd.client.prenom}"/></td>
        <td>
          <a href="${pageContext.request.contextPath}/commandes?action=detail&id=${cmd.id}">Détail</a> |
          <a href="${pageContext.request.contextPath}/commandes?action=delete&id=${cmd.id}"
             onclick="return confirm('Supprimer cette commande ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
    <c:if test="${empty commandes}">
      <tr><td colspan="4">Aucune commande.</td></tr>
    </c:if>
  </table>
</body>
</html>