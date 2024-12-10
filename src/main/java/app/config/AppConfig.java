package app.config;

import app.controllers.ExceptionController;
import app.exception.ApiException;
import app.routes.Routes;
import app.util.ApiProps;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AppConfig {
    private static final Routes routes = new Routes();
    private static final ExceptionController exceptionController = new ExceptionController();

    private static void configuration(JavalinConfig config) {
        // == Server ==
        config.router.contextPath = ApiProps.API_CONTEXT;

        // == Plugins ==
        config.bundledPlugins.enableRouteOverview("/routes"); // Enable route overview
        config.bundledPlugins.enableDevLogging(); // Enable development logging

        // == Routes ==
        config.router.apiBuilder(routes.getApiRoutes());
    }

    // == Exception ==
    public static void exceptionHandler(Javalin app) {
        app.exception(ApiException.class, exceptionController::apiExceptionHandler);
        app.exception(Exception.class, exceptionController::exceptionHandler);
    }

    private static void applyCorsHeaders(Context ctx) {
        ctx.header("Access-Control-Allow-Origin", "*");
        ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        ctx.header("Access-Control-Allow-Credentials", "true");
    }

    // == CORS Setup ==
    private static void configureCors(Javalin app) {
        app.before(AppConfig::applyCorsHeaders); // Apply headers to all requests

        app.options("/*", ctx -> {
            applyCorsHeaders(ctx); // Apply headers for preflight requests
            ctx.status(204); // No content response for OPTIONS requests
        });
    }

    // == Start server ==
    public static void startServer() {
        var app = Javalin.create(AppConfig::configuration);

        configureCors(app); // Configure CORS
        exceptionHandler(app); // Set exception handling

        app.error(404, ctx -> ctx.json("Not found")); // Handle 404 errors
        app.start(ApiProps.PORT);
    }

    // == Stop server ==
    public static void stopServer(Javalin app) {
        app.stop();
    }
}
