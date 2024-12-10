package app.controllers;

import app.daos.InvitationDAO;
import app.dtos.InvitationDTO;
import app.entities.Invitation;
import app.exception.ApiException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InvitationController
{
    private final Logger log = LoggerFactory.getLogger(InvitationController.class);
    InvitationDAO invitationDAO;

    public InvitationController(InvitationDAO invitationDAO)
    {
        this.invitationDAO = invitationDAO;
    }

    public void getAllInvitations(Context ctx)
    {
        try
        {
            // == querying ==
            List<Invitation> invitations = invitationDAO.getAll();

            // == response ==
            List<InvitationDTO> invitationDTOS = InvitationDTO.toInvitationDTOList(invitations);
            ctx.res().setStatus(200);
            ctx.json(invitationDTOS, InvitationDTO.class);
        } catch (Exception e)
        {
            log.error("500 {} ", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }
    }

    public void createInvitation(Context ctx)
    {
        try
        {
            // == request ==
            InvitationDTO invitationDTO = ctx.bodyAsClass(InvitationDTO.class);

            // == querying ==
            Invitation invitation = new Invitation(invitationDTO);
            invitationDAO.create(invitation);

            // == response ==
            ctx.res().setStatus(201);
            ctx.result("Hotel created");
        } catch (Exception e)
        {
            // Log an error if there is an error
            log.error("400 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(400, e.getMessage());
        }
    }

    public void deleteInvitation(Context ctx)
    {
        try
        {
            // Extract the id from the path parameter
            String id = ctx.pathParam("id");

            // Query the database to delete the invitation
            invitationDAO.delete(Long.parseLong(id));

            // Set response status to 204 No Content
            ctx.status(204).result("Invitation deleted");
        } catch (Exception e)
        {
            // Log an error if there is an error
            log.error("Error deleting invitation: {}", e.getMessage());

            // Throw our own exception with status 404 if the resource is not found or 400 for other issues
            throw new ApiException(404, "Invitation not found or could not be deleted");
        }
    }


}
