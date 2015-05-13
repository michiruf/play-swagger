package models.swagger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.models.Swagger;
import com.wordnik.swagger.util.Yaml;
import play.Logger;
import play.Play;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Provides loading of specific configuration and applying it to our
 * bean configuration.
 *
 * @author Michael Ruf
 * @since 2015-05-05
 */
public class BeanConfigResource {

    public static BeanConfigResource init() {
        return new BeanConfigResource();
    }

    private Swagger swaggerForConfiguration;

    private BeanConfigResource() {
        try {
            swaggerForConfiguration = Yaml.mapper().readValue(new FileInputStream(
                    Play.getFile("conf/swagger.yml")), Swagger.class);
        } catch (FileNotFoundException e) {
            Logger.error("File conf/swagger.yml could not be found. Swagger running in default mode.");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToBeanConfig(BeanConfig beanConfig) {
        if (swaggerForConfiguration != null) {
            beanConfig.setInfo(swaggerForConfiguration.getInfo());
            beanConfig.setHost(swaggerForConfiguration.getHost());
            beanConfig.setBasePath(swaggerForConfiguration.getBasePath());
        }
    }

}
