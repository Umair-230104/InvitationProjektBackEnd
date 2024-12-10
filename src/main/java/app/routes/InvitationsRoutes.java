package app.routes;

import app.config.HibernateConfig;
import app.controllers.InvitationController;
import app.daos.InvitationDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;


public class InvitationsRoutes
{

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("invitationdb");
    private final InvitationDAO invitationDAO = new InvitationDAO(emf);
    private final InvitationController invitationController = new InvitationController(invitationDAO);

    public EndpointGroup getInvitationRoutes() {
        return () -> {
            get("/", invitationController::getAllInvitations);
            post("/", invitationController::createInvitation);
            delete("/{id}", invitationController::deleteInvitation); // Fixed the quote issue
        };
    }

}
