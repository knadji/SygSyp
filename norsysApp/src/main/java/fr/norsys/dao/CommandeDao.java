package fr.norsys.dao;

import java.util.List;

import fr.norsys.beans.Commande;

public interface CommandeDao {

    void creer( Commande commande );

    Commande trouver( long id );

    List<Commande> lister();

    void supprimer( long id );

}
