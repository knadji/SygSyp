package fr.norsys.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.norsys.beans.Client;
import fr.norsys.dao.BaseDao;
import fr.norsys.dao.ClientDao;
import fr.norsys.dao.mapper.ClientRowMapper;

@Repository
public class ClientDaoImpl implements ClientDao {

    private static final Logger   LOGGER = LoggerFactory.getLogger( ClientDaoImpl.class );

    @Autowired
    private final BaseDao<Client> baseDao;

    @Autowired
    public ClientDaoImpl( final DataSource dataSource ) {
        this.baseDao = new BaseDao<>( dataSource );
    }

    @Override
    public void creer( final Client client ) {
        final int updated = baseDao
                .mettreAjour(
                        "UPDATE Client SET nom = ?, prenom = ?, adresse = ?, telephone = ?, WHERE idClient = ?",
                        client.getNom(), client.getPrenom(), client.getAdresseDeLivraison(),
                        client.getNumTel(), client.getId() );
        if ( updated == 0 ) {
            LOGGER.info( "Erreur à l'enregistrement du client" );
        }
    }

    @Override
    public void inscriptionClient( final Client client ) {
        final int updated = baseDao
                .mettreAjour(
                        "INSERT INTO Client ( idClient, mdp, email) VALUES (idClient_seq.NEXTVAL,?,?)",
                        client.getMotDePasse(), client.getAdresseMail() );
        if ( updated == 0 ) {
            LOGGER.info( "Erreur à l'enregistrement du client" );
        }
    }

    @Override
    public List<Client> lister() {
        return baseDao.trouverTous(
                "SELECT idClient, nom, prenom, adresse, telephone, email FROM Client ORDER BY idClient",
                new ClientRowMapper() );
    }

    @Override
    public void supprimer( final long id ) {
        final int supprimer = baseDao.mettreAjour( "DELETE FROM Client WHERE idClient = :id", id );
        if ( supprimer == 0 ) {
            LOGGER.info( "Echec de suppression d'un client." );
        }
    }

    @Override
    public Client trouver( final long id ) {
        return baseDao.trouver(
                "SELECT idClient, nom, prenom, adresse, telephone, email FROM Client WHERE idClient = ?",
                new ClientRowMapper(), id );
    }

    @Override
    public Client trouver( final String email ) {
        return baseDao.trouver(
                "SELECT idClient, nom, prenom, adresse, telephone, email FROM Client WHERE email = ?",
                new ClientRowMapper(), email );
    }

    @Override
    public Client connexionClient( final String emailClient, final String mdpClient ) {
        return baseDao
                .trouver(
                        "SELECT idClient, nom, prenom, adresse, telephone, email FROM Client WHERE Client.email = ? AND Client.mdp = ?",
                        new ClientRowMapper(), emailClient, mdpClient );
    }
}
