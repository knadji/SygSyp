<c:import url="../includes/header.jsp"/>
<body>
	<c:import url="../includes/menu.jsp" />
	<div id="corps">
			<p class="info">${form.resultat}</p>
			<b>Information sur le Client : </b>
			<br />
			<p class="info">${message}</p>
			<p>Nom : <c:out value="${commande.client.nom}"/></p>
			<p>Prénom : <c:out value="${commande.client.prenom}"/></p>
			<p>Adresse : <c:out value="${commande.client.adresseDeLivraison}"/></p>
			<p>Numéro de téléphone : <c:out value="${commande.client.numTel}"/></p>
			<p>Email : <c:out value="${commande.client.adresseMail}"/></p>

			<b>Information sur la commande : </b>
			<br />
			<p>Date : <c:out value="${commande.date}"/></p>
			<p>Montant commande : <c:out value="${commande.montant}"/></p>
			<p>Mode de paiement : <c:out value="${commande.modeDePaiement}"/></p>
			<p>Statut paiement cmd : <c:out value="${commande.statutDePaiement}"/></p>
			<p>Mode de livraison : <c:out value="${commande.modeDeLivraison}"/></p>
			<p>Statut de livraison : <c:out value="${commande.statutDeLivraison}"/></p>
	</div>
</body>
</html>