<c:import url="../includes/header.jsp"/>
    <body>
    	<c:import url="../includes/menu.jsp"/>
        <div>
            <form method=post action="<c:url value="/creationCommande"/>">
            
            	<fieldset>
                    <legend>Informations client</legend>
                    
                    <%-- Vérifier si la Map des clients en session n'est pas vide, alors en propose un choix à l'utilisateur --%>
                    <c:if test="${!empty listeClient }">
                    	<label for="choixNouveauClient">Nouveau client ? <span class="requis">*</span></label>
                    	<input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient" checked="checked" /> Oui
                    	<input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="ancienClient"/> Non
                    	<br/><br/>
                    </c:if>
                    
                    <c:set var="client" value="${commande.client}" scope="request" />
                    <div id="nouveauClient">
    					<c:import url="../includes/inc_client_form.jsp" />
                	</div>
                	
                	<%-- Création d'une liste déroulante si le map user est vide --%>
                	<c:if test="${!empty listeClient }">
                		<div id="ancienClient">
				         	<select name="listeClients" id="listeClients">
				         		<option value="">Choisissez un client...</option>
				         		<c:forEach items="${listeClient }" var="Client">
				         			<option value="${Client.id}">${Client.prenom} ${Client.nom}</option>
				         		</c:forEach>
				         	</select>   		
                		</div>
                	</c:if>
                </fieldset>
                
                <fieldset>
                    <legend>Informations commande</legend>
                    
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="dateCommande" name="dateCommande" value="<c:out value="${commande.date}"/>" size="30" maxlength="30" disabled />
                    <br />
                    
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="${commande.montant}" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['montantCommande']}</span>
                    <br />
                    
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="${commande.modeDePaiement}" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['modePaiementCommande']}</span>
                    <br />
                    
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="${commande.statutDePaiement }" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['statutPaiementCommande']}</span>
                    <br />
                    
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="${commande.modeDeLivraison }" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['modeLivraisonCommande']}</span>
                    <br />
                    
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="${commande.statutDeLivraison}" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['statutLivraisonCommande']}</span>
                    <br />
                    <p class="info">${form.resultat}</p>
                </fieldset>
                
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
        
        <%-- Affichage dynamique avec jQuery --%>
        
       <script>
       		jQuery(document).ready(function(){
       			/* On cache les informations des clients existants */
       			$("div#ancienClient").hide();
       			jQuery('input[name=choixNouveauClient]:radio').click(function(){
       				$("div#nouveauClient").hide();
					$("div#ancienClient").hide();
					var divId = jQuery(this).val();
					$("div#"+divId).show();
       			});
       		});
       </script>
    </body>
</html>