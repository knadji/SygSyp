package fr.norsys.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.norsys.beans.Client;
import fr.norsys.beans.Commande;
import fr.norsys.dao.BaseDao;
import fr.norsys.dao.CommandeDao;
import fr.norsys.dao.mapper.ClientRowMapper;

@Repository
public class CommandeDaoImpl implements CommandeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger( CommandeDaoImpl.class);
    
    @Autowired
    private final BaseDao<Commande> baseDao;

    @Autowired
    public CommandeDaoImpl( final DataSource dataSource ) {
        this.baseDao = new BaseDao<>( dataSource );
    }

    @Override
    public void creer( final Commande commande ) {
        final int update = baseDao
                .mettreAjour(
                        "INSERT INTO Commande (idCommande, idClient, dateCommande, montant, mode_paiement, statut_paiement, mode_livraison, statut_livraison)"
                                + " VALUES (idCommande_seq.NEXTVAL,?,?,?,?,?,?,?)",
                        commande.getClient().getId(), commande.getDate(), commande.getMontant(),
                        commande.getModeDePaiement(), commande.getStatutDePaiement(),
                        commande.getModeDeLivraison(),
                        commande.getStatutDeLivraison() );
        if ( update == 0 ) {
            LOGGER.info( "Echec de l'insertion de la commande" );
        }
    }

    @Override
    public Commande trouver( final long id ) {
        return baseDao.trouver(
                "SELECT * FROM Commande inner join CLIENT on client.idClient = Commande.idClient WHERE idCommande = ?",
                this::map, id );
    }

    @Override
    public List<Commande> lister() {
        return baseDao
                .trouverTous(
                        "SELECT * FROM Commande INNER JOIN Client on client.idClient = commande.idClient",
                        this::map );
    }

    @Override
    public void supprimer( final long id ) {
        final int supprimer = baseDao.mettreAjour( "DELETE FROM Commande WHERE idCommande = ?", id );
        if ( supprimer == 0 ) {
            LOGGER.info( "Echec de la suppression de la commande." );
        }
    }

    private Commande map( final ResultSet resultat, final int i ) throws SQLException {
        final Commande commande = new Commande();
        commande.setId( resultat.getLong( "idCommande" ) );

        final ClientRowMapper clientRowMapper = new ClientRowMapper();
        final Client client = clientRowMapper.mapRow( resultat, i );
        commande.setClient( client );

        commande.setDate( resultat.getString( "dateCommande" ) );
        commande.setMontant( resultat.getDouble( "montant" ) );
        commande.setModeDePaiement( resultat.getString( "mode_paiement" ) );
        commande.setStatutDePaiement( resultat.getString( "statut_paiement" ) );
        commande.setModeDeLivraison( resultat.getString( "mode_livraison" ) );
        commande.setStatutDeLivraison( resultat.getString( "statut_paiement" ) );

        return commande;
    }
}
