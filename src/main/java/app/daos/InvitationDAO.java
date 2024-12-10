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

    public void delete(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Invitation invitation = em.find(Invitation.class, id);
            if (invitation != null) {
                em.remove(invitation);
            } else {
                throw new Exception("Invitation not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }


}
