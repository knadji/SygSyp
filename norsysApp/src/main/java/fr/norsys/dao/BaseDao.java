package fr.norsys.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BaseDao<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger( BaseDao.class );

    private final JdbcTemplate  jdbcTemplate;

    @Autowired
    public BaseDao( final DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    public T trouver( final String requeteSql, final RowMapper<T> mapper, final Object... objects ) throws DAOException {
        T result = null;
        try {
            result = jdbcTemplate.queryForObject( requeteSql, mapper, objects );
        } catch ( final EmptyResultDataAccessException e ) {
            LOGGER.debug( "Pas d'élément remontés", e );
        }
        return result;
    }

    public List<T> trouverTous( final String requeteSql, final RowMapper<T> mapper, final Object... objects )
            throws DAOException {
        return jdbcTemplate.query( requeteSql, mapper, objects );
    }

    public int mettreAjour( final String sql, final Object... objects ) throws DAOException {
        int result = 0;
        try {
            result = jdbcTemplate.update( sql, objects );
        } catch ( final Exception e ) {
            LOGGER.debug( "DataAccessException catcher : ", e.getMessage() );
        }
        return result;
    }
}
