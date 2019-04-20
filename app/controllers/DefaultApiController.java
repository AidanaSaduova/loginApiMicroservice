package controllers;

import apimodels.UserLoginApiForm;
import apimodels.UserViewModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import handler.LoginHandler;
import handler.SimpleLoginHandler;
import play.Configuration;
import play.mvc.Controller;
import play.mvc.Result;
import swagger.SwaggerUtils;
import swagger.SwaggerUtils.ApiAction;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2019-04-20T20:49:34.098+02:00")

public class DefaultApiController extends Controller {

    private final DefaultApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private DefaultApiController(Configuration configuration, DefaultApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result login() throws Exception {

        LoginHandler loginHandler;
        loginHandler = new SimpleLoginHandler(mapper);

        JsonNode nodeuser = request().body().asJson();
        UserLoginApiForm user;
        if (nodeuser != null) {
            user = mapper.readValue(nodeuser.toString(), UserLoginApiForm.class);
            if (configuration.getBoolean("useInputBeanValidation")) {
                SwaggerUtils.validate(user);
            }
        } else {
            user = null;
        }
        UserViewModel obj = imp.login(user);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            SwaggerUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
