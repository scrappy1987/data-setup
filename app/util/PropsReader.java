package util;

import play.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by a585493 on 26/10/2015.
 */
public class PropsReader {

    private final Logger.ALogger logger = Logger.of(this.getClass());
    private Properties props;
    private InputStream input;

    public PropsReader(String propsLocation) {
        this.props = new Properties();
        loadProperties(propsLocation);
    }

    public String getSqlFileLocation(Long id) {
        return props.getProperty(id.toString());
    }

    private void loadProperties(String propsLocation) {
        try {
            this.input = new FileInputStream(propsLocation);
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
