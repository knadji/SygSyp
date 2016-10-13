<c:import url="../includes/header.jsp"/>
<body>
	<c:import url="../includes/menu.jsp" />
	<div id="corps">
		<p class="info">${form.resultat}</p>
		<p>Nom : <c:out value="${client.nom}"/></p>
		<p>Prénom : <c:out value="${client.prenom}"/></p>
		<p>Adresse : <c:out value="${client.adresseDeLivraison}"/></p>
		<p>Numéro de téléphone : <c:out value="${client.numTel}"/></p>
		<c:if test="${!empty sessionScope.sessionClient}">
			<p class="succes">Connecté avec une session : ${sessionScope.sessionClient.nom}</p>
		</c:if>
	</div>
</body>
</html>