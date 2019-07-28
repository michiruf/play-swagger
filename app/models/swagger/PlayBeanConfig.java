package models.swagger;

import io.swagger.annotations.Api;
import io.swagger.config.Scanner;
import io.swagger.config.ScannerFactory;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.AbstractScanner;
import io.swagger.models.Swagger;
import play.Play;

import java.util.HashSet;
import java.util.Set;

/**
 * As we do not want to need to specify packages instead using
 * the Play class loader we use this extended bean config to
 * append our play classes.
 * @author Michael Ruf
 * @since 2015-05-05
 */
public class PlayBeanConfig extends AbstractScanner implements Scanner {

    private Reader reader;

    PlayBeanConfig(Swagger base) {
        this.reader = new Reader(base);
    }

    void enableScan() {
        reader.read(classes());
        ScannerFactory.setScanner(this);
    }

    Swagger getSwagger() {
        return this.reader.getSwagger();
    }

    @Override
    public Set<Class<?>> classes() {
        Set<Class<?>> classes = new HashSet<>();
        for (Class<?> clazz : Play.classloader.getAnnotatedClasses(Api.class)) {
            classes.add(clazz);
        }
        return classes;
    }
}
