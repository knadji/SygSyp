<c:import url="../includes/header.jsp"/>

    <form method="post" action="inscription">
 		<fieldset>
        <legend>Inscription</legend>
        <p>Vous pouvez vous inscrire via ce formulaire.</p>

        <label for="email">Adresse email <span class="requis">*</span></label>
        <input type="text" id="email" name="emailClient" value="" size="20" maxlength="60" />
        <span class="erreur">${form.erreurs['emailClient']}</span>
        <br />

        <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="motdepasse" name="motDePasse" value="" size="20" maxlength="20" />
        <span class="erreur">${form.erreurs['motDePasse']}</span>
        <br />

        <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
        <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
        <br />

        <input type="submit" value="Inscription" class="sansLabel" />
        <br />
        
        <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${resultat}</p>
      </fieldset>
    </form>
    
<c:import url="../includes/footer.jsp"/>
