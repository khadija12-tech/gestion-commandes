<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Client</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <h2><c:out value="${empty client ? 'Ajouter' : 'Modifier'}"/> un client</h2>

  <form action="${pageContext.request.contextPath}/clients" method="post">
    <input type="hidden" name="id" value="${client.id}">
    <p>Nom : <input type="text" name="nom" value="<c:out value='${client.nom}'/>" required></p>
    <p>Prénom : <input type="text" name="prenom" value="<c:out value='${client.prenom}'/>" required></p>
    <p>Email : <input type="email" name="email" value="<c:out value='${client.email}'/>"></p>
    <p>Téléphone : <input type="text" name="telephone" value="<c:out value='${client.telephone}'/>"></p>
    <button type="submit">Enregistrer</button>
    <a href="${pageContext.request.contextPath}/clients">Annuler</a>
  </form>
</body>
</html>