package app.daos;

import app.entities.Invitation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class InvitationDAO
{
    private final EntityManagerFactory emf;

    public InvitationDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public List<Invitation> getAll()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT i FROM Invitation i", Invitation.class).getResultList();
        }
    }

    public void create(Invitation invitation)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(invitation);
            em.getTransaction().commit();
        }
    }
}
