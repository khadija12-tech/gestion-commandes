<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Produit</title></head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2><c:out value="${empty produit ? 'Ajouter' : 'Modifier'}"/> un produit</h2>
  <form action="${pageContext.request.contextPath}/produits" method="post">
    <input type="hidden" name="id" value="${produit.id}">
    <p>Désignation : <input type="text" name="designation" value="<c:out value='${produit.designation}'/>" required></p>
    <p>Prix : <input type="number" step="0.01" min="0" name="prix" value="${produit.prix}" required></p>
    <p>Stock : <input type="number" min="0" name="stock" value="${produit.stock}" required></p>
    <button type="submit">Enregistrer</button>
    <a href="${pageContext.request.contextPath}/produits">Annuler</a>
  </form>
</body>
</html>