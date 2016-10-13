<c:import url="includes/header.jsp" />

<div id="banniere_image">
	<div id="banniere_description">
		<h2>Le projet de loi de financement de la sécurité sociale 2016 ...</h2>
		<a href="<c:url value="http://www.gouvernement.fr/action/le-projet-de-loi-de-financement-de-la-securite-sociale-2016"/>" class="bouton_rouge">Voir
			l'article<img src="<c:url value="/img/flecheblanchedroite.png"/>" />
		</a>
	</div>
</div>
<section>
	<aside>
		<center><h1>Importer une application</h1></center>
		<p id="photo_zozor">
			<img src="<c:url value="/img/import1.png"/>"/>
		</p>
		<form action="<c:url value="/UploadFile"/>" method="post" enctype="multipart/form-data">
			<input type="file" name="fichier"/>
			<input type="submit" value="Envoyer">
			<br/>
			<p><span class="erreur">${form.erreurs['fichier']}</span></p>
			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	    </form>
		<p>Importer une application de type war pour la surveille.</p>
	</aside>
	
	<aside>
		<center><h1>Configuration</h1></center>
		<p id="photo_zozor">
			<a href="#"><img src="<c:url value="/img/conf.png"/>"/></a>
		</p>
		<p>Configuration des sondes à récupérer à partir de votre application.</p>
	</aside>
	
	<aside>
		<center><h1>Visualisation</h1></center>
		<p id="photo_zozor">
			<a href="<c:url value="http://192.168.100.68:8080/" />"><img src="<c:url value="/img/moni.png"/>"/></a>
		</p>
		<p><h1>Visualisation des mesures en temps réel.</h1></p>
	</aside>

</section>
<c:import url="includes/footer.jsp"></c:import>

<!-- 	<div align="right"> -->
<%-- 		<c:if test="${empty sessionScope.sessionUtilisateur}"> --%>
<%-- 		<form method="POST" action="<c:url value="/index"/>"> --%>
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td align="right"><label for="Mail">Email :</label></td> -->
<!-- 					<td><input type="text" placeholder="Votre Mail" id="Mail" -->
<!-- 						name="emailClient" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="right"><label for="password">Mot de passe :</label> -->
<!-- 					</td> -->
<!-- 					<td><input type="password" placeholder="Votre Mot de passe" -->
<!-- 						id="password" name="motDePasse" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td></td> -->
<!-- 					<td align="center"><input type="submit" value="Connexion" -->
<!-- 						name="formConnexion" /></td> -->
<!-- 				</tr> -->
<!-- 		</form> -->
<!-- 			</table> -->
<!-- 				<div align="right"> -->
<%-- 					<a href="<c:url value="/inscription"/>">S'inscrire</a> | --%>
<%-- 				    <a href="<c:url value="/nouveauMdp"/>">Mot de passe oublié! </a> --%>
<!-- 				</div> -->
<%-- 		</c:if> --%>
<!-- 	</div> -->
<!-- 	<div id="menu_gauche"> -->
<!-- 		<ul> -->
<%-- 			<li><a href="<c:url value="/creationClient"/>">Création d'un client</a></li> --%>
<%-- 			<li><a href="<c:url value="/creationCommande"/>">Création d'une commande</a></li> --%>
<!-- 		</ul> -->
<!-- 	</div> -->
<!-- 	<br /> -->
<!-- 	<div id="piedpage"> -->
<!-- 		<p>Ceci est le pied de page</p> -->
<!-- 	</div> -->
