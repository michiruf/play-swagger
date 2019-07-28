package models.swagger;

import io.swagger.models.Swagger;
import io.swagger.util.Yaml;
import play.Play;

/**
 * @author Michael Ruf
 * @since 2019-06-10
 */
public class Swaggerton {

    private static final String YAML_CONF_PATH = "conf/swagger.yml";
    private static Swaggerton instance;

    public static Swaggerton get() {
        if (instance == null) {
            synchronized (Swaggerton.class) {
                if (instance == null) {
                    instance = new Swaggerton();
                }
            }
        }
        return instance;
    }

    public static synchronized void reset() {
        instance = null;
    }

    private Swagger swagger;

    private Swaggerton() {
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws Exception {
        Swagger yamlSwagger = Yaml.mapper().readValue(Play.getFile(YAML_CONF_PATH), Swagger.class);
        PlayBeanConfig bean = new PlayBeanConfig(yamlSwagger);
        bean.enableScan();
        swagger = bean.getSwagger();
    }

    public Swagger getSwagger() {
        return swagger;
    }
}
