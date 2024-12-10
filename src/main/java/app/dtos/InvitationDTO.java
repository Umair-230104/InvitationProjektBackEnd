package app.dtos;

import app.entities.Invitation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public InvitationDTO(Invitation invitation) {
        this.id = invitation.getId();
        this.name = invitation.getName();
        this.phoneNumber = invitation.getPhoneNumber();
        this.people = invitation.getPeople();
        this.createdAt = invitation.getCreatedAt();
    }

    public static List<InvitationDTO> toInvitationDTOList(List<Invitation> invitations) {
        return invitations.stream().map(InvitationDTO::new).collect(Collectors.toList());
    }
}
