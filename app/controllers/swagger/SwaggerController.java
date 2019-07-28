package controllers.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.models.Swagger;
import models.swagger.Swaggerton;
import play.Play;
import play.mvc.Controller;
import play.mvc.results.RenderJson;

/**
 * @author Michael Ruf
 * @since 2015-05-03
 */
public class SwaggerController extends Controller {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static void json() throws RenderJson {
        // If in dev mode, reset the swagger configuration to rebuild it without restarting the server
        if (Play.mode.isDev())
            Swaggerton.reset();

        // Get the swagger object
        Swagger swaggerObject = Swaggerton.get().getSwagger();

        // Patch host if necessary
        if (swaggerObject.getHost() == null)
            swaggerObject.setHost(request.host);

        // Output the json
        try {
            throw new RenderJson(mapper.writeValueAsString(swaggerObject));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Output json like swagger intents
        // TODO

    }
}
