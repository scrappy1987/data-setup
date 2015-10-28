package repository;

import com.typesafe.config.ConfigFactory;
import play.Logger;
import util.PropsReaderUtility;
import util.FileReaderUtility;

import javax.inject.Inject;

/**
 * Created by a585493 on 26/10/2015.
 */
public class Dao {

    private static final Logger.ALogger logger = Logger.of(Dao.class);
    private EntityManagerProvider emp;
    private FileReaderUtility sqlReader;

    @Inject
    public Dao(EntityManagerProvider emp, FileReaderUtility sqlReader){
        this.emp = emp;
        this.sqlReader = sqlReader;
    }

    public final String executeQuery(Long id)
    {
        String sqlString = null;
        PropsReaderUtility props = new PropsReaderUtility(ConfigFactory.load().getString("sql.scripts"));
        String sqlFileLocation = props.getFileLocation(id);
        try {
            logger.info("executing query Dao sqlFileLocation: " + sqlFileLocation);
            sqlString = sqlReader.readFile(sqlFileLocation);
            logger.info("executing query Dao sqlString: " + sqlString);
        } catch (Exception e) {
            return "You have encountered a problem -> You most likely have input an invalid script id";
        }
        emp.getEntityManager().createNativeQuery(sqlString).executeUpdate();
        return "Your query has been executed succesfully";
    }
}
