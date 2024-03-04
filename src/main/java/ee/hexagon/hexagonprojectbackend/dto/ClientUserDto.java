package ee.hexagon.hexagonprojectbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;

    public ClientUserDto(Long userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
