package app.dtos;

import app.entities.Invitation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDTO {
    private Long id;
    private String name;
    private int phoneNumber;
    private int people;

    public InvitationDTO(Invitation invitation) {
        this.id = invitation.getId();
        this.name = invitation.getName();
        this.phoneNumber = invitation.getPhoneNumber();
        this.people = invitation.getPeople();
    }

    public static List<InvitationDTO> toInvitationDTOList(List<Invitation> invitations) {
        return invitations.stream().map(InvitationDTO::new).collect(Collectors.toList());
    }
}
