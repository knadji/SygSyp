<%-- <c:import url="includes/header.jsp"/> --%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Commande en ligne</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</head>
<body>
	<div id="bloc_page">
		<header>
			<div id="titre_principale">
				<div id="logo">
					<a href="<c:url value="/index"/>"><img alt="Logo Norsys" src="<c:url value="/img/norsys.png"/>" /></a>
					<h1>Monitoring Applicatif non intrusive</h1>
				</div>
				<div id="form_connexion">
					<c:choose>
						<c:when
							test="${empty sessionScope.sessionUtilisateur.adresseMail }">
							<form method="POST" action="<c:url value="/index"/>">
								<table>
									<tr>
										<td align="right">Email :</td>
										<td><input type="text" placeholder="Votre Mail" id="Mail"
											name="emailClient" /></td>
									</tr>
									<tr>
										<td align="right">Mot de passe :</td>
										<td><input type="password"
											placeholder="Votre Mot de passe" id="password"
											name="motDePasse" /></td>
									</tr>
									<tr>
										<td></td>
										<td align="center"><input type="submit" value="Connexion"
											name="formConnexion" /></td>
									</tr>
							</form>
								</table>
								<div align="right">
									<a href="<c:url value="/inscription"/>">S'inscrire</a> | <a
										href="<c:url value="/nouveauMdp"/>">Mot de passe oublié! </a>
								</div>
						</c:when>
						<c:otherwise>
							<p>Bonjour, ${sessionScope.sessionUtilisateur.adresseMail}</p>
							<p>
								<a href="<c:url value="/"/>">Accueil</a> | <a href="<c:url value="/deconnexion"/>">Se déconnecter</a>
							</p>
							<p>
								<a href="<c:url value="/creationClient"/>">Compléter mon profil</a>
							</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</header>