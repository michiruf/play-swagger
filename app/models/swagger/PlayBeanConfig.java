package models.swagger;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.jaxrs.config.BeanConfig;
import play.Play;

import java.util.HashSet;
import java.util.Set;

/**
 * As we do not want to need to specify packages instead using
 * the Play class loader we use this extended bean config to
 * append our play classes.
 * <br><br>
 * The default line we skip would be use:
 * <pre>
 * beanConfig.setResourcePackage("controllers,models"); // [,...] (CSV)
 * </pre>
 *
 * @author Michael Ruf
 * @since 2015-05-05
 */
public class PlayBeanConfig extends BeanConfig {

    @Override
    public Set<Class<?>> classes() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        for (Class<?> clazz : Play.classloader.getAnnotatedClasses(Api.class)) {
            classes.add(clazz);
        }
        return classes;
    }
}
