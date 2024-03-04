package ee.hexagon.hexagonprojectbackend.mapper;

import ee.hexagon.hexagonprojectbackend.dto.ClientUserDto;
import ee.hexagon.hexagonprojectbackend.model.ClientUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientUserMapper {
    @Mapping(target = "userId", source = "clientUser.userId")
    @Mapping(target = "firstName", source = "clientUser.firstName")
    @Mapping(target = "lastName", source = "clientUser.lastName")
    @Mapping(target = "email", source = "clientUser.email")
    ClientUserDto clientUserToClientUserDto(ClientUser clientUser);
}
