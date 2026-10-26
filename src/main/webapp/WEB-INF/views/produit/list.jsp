<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Produits</title></head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2>Liste des produits</h2>
  <a href="${pageContext.request.contextPath}/produits?action=new">+ Nouveau produit</a>
  <br><br>
  <table border="1">
    <tr><th>ID</th><th>Désignation</th><th>Prix</th><th>Stock</th><th>Actions</th></tr>
    <c:forEach var="p" items="${produits}">
      <tr>
        <td>${p.id}</td>
        <td><c:out value="${p.designation}"/></td>
        <td>${p.prix}</td>
        <td>${p.stock}</td>
        <td>
          <a href="${pageContext.request.contextPath}/produits?action=edit&id=${p.id}">Modifier</a> |
          <a href="${pageContext.request.contextPath}/produits?action=delete&id=${p.id}"
             onclick="return confirm('Supprimer ce produit ?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
    <c:if test="${empty produits}">
      <tr><td colspan="5">Aucun produit.</td></tr>
    </c:if>
  </table>
</body>
</html>