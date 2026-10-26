<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Détail commande</title></head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2>Commande n° ${commande.id}</h2>
  <p>Date : ${commande.dateCommande} | Client : <c:out value="${commande.client.nom} ${commande.client.prenom}"/></p>

  <table border="1">
    <tr><th>Produit</th><th>Quantité</th><th>Prix unitaire</th><th>Total</th><th></th></tr>
    <c:set var="total" value="0"/>
    <c:forEach var="l" items="${commande.lignes}">
      <tr>
        <td><c:out value="${l.produit.designation}"/></td>
        <td>${l.quantite}</td>
        <td>${l.prixUnitaire}</td>
        <td>${l.quantite * l.prixUnitaire}</td>
        <td><a href="${pageContext.request.contextPath}/commandes?action=deleteLigne&id=${l.id}&commandeId=${commande.id}">X</a></td>
      </tr>
      <c:set var="total" value="${total + l.quantite * l.prixUnitaire}"/>
    </c:forEach>
    <tr><td colspan="3"><b>Total</b></td><td colspan="2"><b>${total}</b></td></tr>
  </table>

  <h3>Ajouter une ligne</h3>
  <form action="${pageContext.request.contextPath}/commandes" method="post">
    <input type="hidden" name="action" value="addLigne">
    <input type="hidden" name="commandeId" value="${commande.id}">
    Produit :
    <select name="produitId">
      <c:forEach var="p" items="${produits}">
        <option value="${p.id}"><c:out value="${p.designation}"/> (${p.prix})</option>
      </c:forEach>
    </select>
    Quantité : <input type="number" name="quantite" min="1" value="1" required>
    <button type="submit">Ajouter</button>
  </form>
  <p><a href="${pageContext.request.contextPath}/commandes">Retour aux commandes</a></p>
</body>
</html>