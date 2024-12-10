package app.entities;

import app.dtos.InvitationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invitation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false)
    private int people;

    public Invitation(InvitationDTO invitationDTO)
    {
        this.name = invitationDTO.getName();
        this.phoneNumber = invitationDTO.getPhoneNumber();
        this.people = invitationDTO.getPeople();
    }

}
