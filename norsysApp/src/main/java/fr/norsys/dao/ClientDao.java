package fr.norsys.dao;

import java.util.List;

import fr.norsys.beans.Client;

public interface ClientDao {

    void creer( Client client );

    void inscriptionClient( Client client );

    Client trouver( long id );

    Client trouver( String email );

    List<Client> lister();

    void supprimer( long id );

    Client connexionClient( String emailClient, String mdpClient );
}
