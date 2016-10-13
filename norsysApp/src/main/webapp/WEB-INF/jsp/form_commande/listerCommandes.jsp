<c:import url="../includes/header.jsp"/>
    <body>
    	<c:import url="../includes/menu.jsp"/>
        <div id="corps">
        <c:choose>
        	<%-- Si aucun commande existe, afficher un messge par défaut --%>
        	<c:when test="${empty listeCommande}">
        		<p class="erreur">Aucune commande enregistrée.</p>
        	</c:when>
        	<%-- sinon affichage de la table des commandes --%>
        	<c:otherwise>
	        	<table id="tab">
	        		<tr>
	        			<th>Client</th>
	        			<th>Date</th>
	        			<th>Montant</th>
	        			<th>Mode de paiement </th>
	        			<th>Statut de paiement</th>
	        			<th>Mode de livraison</th>
	        			<th>Statut de livraison</th>
	        			<th class="action">Action</th>
	        		</tr>
	       			<%-- parcoure de la liset des commandes --%>
	       			<c:forEach items="${listeCommande}" var="commande" varStatus="boucle">
	       				<tr class="${bloucle.index % 2 == 0 ? 'pair' : 'impair'}">
	       					<td><c:out value="${commande.client.prenom} ${commande.client.nom }"></c:out></td>
	       					<td><c:out value="${commande.date }"/></td>
	       					<td><c:out value="${commande.montant }"/></td>
	       					<td><c:out value="${commande.modeDePaiement }"/></td>
	       					<td><c:out value="${commande.statutDePaiement }"/></td>
	       					<td><c:out value="${commande.modeDeLivraison }"/></td>
	       					<td><c:out value="${commande.statutDeLivraison }"/></td>
	       					<td class="action">
	       						<a href="<c:url value="/suppressionCommande"> <c:param name="idCommande" value="${commande.id}"/> </c:url>">
	       							<img src="<c:url value="/img/supprimer.png"/>" alt="Supprimer"/>
	       						</a>
	       					</td>
	       				</tr>
	       			</c:forEach>
	        	</table>
        	</c:otherwise>
        	</c:choose>
        </div>
    </body>
</html>