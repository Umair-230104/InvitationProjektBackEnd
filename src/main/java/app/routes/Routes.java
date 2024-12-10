package app.routes;


import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes
{

    private final InvitationsRoutes invitationRoute = new InvitationsRoutes();

    public EndpointGroup getApiRoutes()
    {
        return () ->
        {
            path("/invitations", invitationRoute.getInvitationRoutes());
        };
    }
}
