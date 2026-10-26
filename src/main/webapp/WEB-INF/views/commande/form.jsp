<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Nouvelle commande</title></head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2>Nouvelle commande</h2>
  <form action="${pageContext.request.contextPath}/commandes" method="post">
    <input type="hidden" name="action" value="save">
    <p>Date : <input type="date" name="date" required></p>
    <p>Client :
      <select name="clientId" required>
        <c:forEach var="cl" items="${clients}">
          <option value="${cl.id}"><c:out value="${cl.nom} ${cl.prenom}"/></option>
        </c:forEach>
      </select>
    </p>
    <button type="submit">Créer</button>
    <a href="${pageContext.request.contextPath}/commandes">Annuler</a>
  </form>
</body>
</html>