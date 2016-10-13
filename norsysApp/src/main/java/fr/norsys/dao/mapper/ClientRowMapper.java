package fr.norsys.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.norsys.beans.Client;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow( final ResultSet resultat, final int rowNum ) throws SQLException {
        final Client client = new Client();
        client.setId( resultat.getLong( "idClient" ) );
        client.setNom( resultat.getString( "nom" ) );
        client.setPrenom( resultat.getString( "prenom" ) );
        client.setAdresseDeLivraison( resultat.getString( "adresse" ) );
        client.setNumTel( resultat.getString( "telephone" ) );
        client.setAdresseMail( resultat.getString( "email" ) );
        return client;
    }

}
