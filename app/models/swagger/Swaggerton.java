package models.swagger;

import com.wordnik.swagger.models.Swagger;

/**
 * Singleton instance to keep the swagger information in.
 *
 * @author Michael Ruf
 * @since 2015-05-03
 */
public class Swaggerton {

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

    private PlayBeanConfig beanConfig;

    private Swaggerton() {
        // Note that with swagger 1.5.0-M1 the error occurred,
        // that the setScan() and more specifically the classes()
        // methods returned an NullPointerException.
        // Keep this in mind whenever you change the dependencies.

        beanConfig = new PlayBeanConfig();
        BeanConfigResource.init().appendToBeanConfig(beanConfig);
        // This just scans the stuff instead of being a setter
        beanConfig.setScan(true);
    }

    public Swagger getSwagger() {
        return beanConfig.getSwagger();
    }

}
