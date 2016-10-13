<c:import url="../includes/header.jsp"/>
    <body>
    	<c:import url="../includes/menu.jsp"/>
        <div id="corps">
        <c:choose>
        	<%-- Si aucun client n'existe, afficher un messge par défaut --%>
        	<c:when test="${empty listeClient}">
        		<p class="erreur">Aucun client enregistré.</p>
        	</c:when>
        	<%-- sinon affichage de la table des clients --%>
        	<c:otherwise>
	        	<table id="tab">
	        		<tr>
	        			<th>Nom</th>
	        			<th>Prénom</th>
	        			<th>Adresse</th>
	        			<th>Téléphone</th>
	        			<th>Email</th>
	        			<th class="action">Action</th>
	        		</tr>
	       			<%-- parcoure de la liste des clients --%>
	       			<c:forEach items="${listeClient}" var="client" varStatus="boucle">
	       				<tr class="${bloucle.index % 2 == 0 ? 'pair' : 'impair'}">
	       					<td><c:out value="${client.nom }"></c:out></td>
	       					<td><c:out value="${client.prenom }"></c:out></td>
	       					<td><c:out value="${client.adresseDeLivraison }"></c:out></td>
	       					<td><c:out value="${client.numTel }"></c:out></td>
	       					<td><c:out value="${client.adresseMail }"></c:out></td>
	       					<td class="action">
	       						<a href=" <c:url value="/suppressionClient"> <c:param name="idClient" value="${client.id}" /> </c:url> "> 
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