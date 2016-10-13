<c:import url="../includes/header.jsp"/>
<body>
    <c:if test="${empty sessionScope.sessionUtilisateur.adresseMail}">
		<c:import url="../includes/menu.jsp" />
	</c:if>
	<div>
		<form method="post" action="<c:url value="/creationClient"/>">
			<fieldset>
				<legend>Informations client</legend>
				<c:import url="../includes/inc_client_form.jsp" />
			</fieldset>
			<p class="info">${form.resultat}</p>
			<input type="submit" value="Valider" /> <input type="reset"
				value="Remettre à zéro" /> <br />
		</form>
	</div>
</body>
</html>