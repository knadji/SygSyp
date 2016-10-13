<c:import url="../includes/header.jsp"/>
<body>
    <h3><p style="color: #800040;">
        Vous avez perdu votre mot de passe
    </p></h3> <br />
    <form method="post" action="inscription">
 		<fieldset>
	        <legend>Nouveau mot de passe</legend>
	
	        <label for="email">Adresse email : <span class="requis">*</span></label>
	        <input type="email" id="email" name="emailClient" value="" size="20" maxlength="60" />
	        <span class="erreur">${erreurs['emailClient']}</span>
	        <br />
	
	        <label for="confirmationDeMail">Confirmation : <span class="requis">*</span> </label>
			<input type="email" id="confirmationDeMail" name="confirmationDeMail" value="" size="20" maxlength="60" />	
	        <span class="erreur">${erreurs['confirmationDeMail']}</span>
	        <br />
	        <input type="submit" value="Envoyer" class="sansLabel" />
	        <br />
	        <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
        </fieldset>
    </form>
    </body>
</html>