<%@ page pageEncoding="UTF-8" %>
<label for="nomClient">Nom <span class="requis">*</span></label>
<input type="text" id="nomClient" name="nomClient" value="<c:out value="${client.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomClient']}</span>
<br />

<label for="prenomClient">Prénom </label>
<input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${client.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomClient']}</span>
<br />

<label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
<input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${client.adresseDeLivraison}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['adresseClient']}</span>
<br />

<label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="telephoneClient" name="telephoneClient" value="<c:out value="${client.numTel}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['telephoneClient']}</span>
<br />

