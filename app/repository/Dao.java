package repository;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.Logger;
import play.api.Play;
import util.PropsReader;
import util.SQLReader;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by a585493 on 26/10/2015.
 */
public class Dao {

    private static final Logger.ALogger logger = Logger.of(Dao.class);
    private EntityManagerProvider emp;
    private SQLReader sqlReader;

    @Inject
    public Dao(EntityManagerProvider emp, SQLReader sqlReader){
        this.emp = emp;
        this.sqlReader = sqlReader;
    }

    public final String executeQuery(Long id)
    {
        String sqlString = null;
        PropsReader props = new PropsReader(ConfigFactory.load().getString("sql.scripts"));
        String sqlFileLocation = props.getSqlFileLocation(id);
        try {
            logger.info("executing query Dao sqlFileLocation: " + sqlFileLocation);
            sqlString = sqlReader.readSQLfile(sqlFileLocation);
            logger.info("executing query Dao sqlString: " + sqlString);
        } catch (Exception e) {
            return "You have encountered a problem -> You most likely have input an invalid script id";
        }
        emp.getEntityManager().createNativeQuery(sqlString).executeUpdate();
        return "Your query has been executed succesfully";
    }
}
