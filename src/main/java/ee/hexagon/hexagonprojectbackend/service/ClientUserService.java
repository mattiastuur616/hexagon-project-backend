package ee.hexagon.hexagonprojectbackend.service;

import ee.hexagon.hexagonprojectbackend.dto.ClientUserDto;
import ee.hexagon.hexagonprojectbackend.mapper.ClientUserMapper;
import ee.hexagon.hexagonprojectbackend.model.ClientUser;
import ee.hexagon.hexagonprojectbackend.repository.ClientUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientUserService {
    private final ClientUserRepository clientUserRepository;
    @Autowired
    private ClientUserMapper clientUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public List<ClientUserDto> getAllUsers() {
        List<ClientUserDto> clientUserDtoList = new ArrayList<>();
        for (ClientUser clientUser : clientUserRepository.findAll()) {
            clientUserDtoList.add(clientUserMapper.clientUserToClientUserDto(clientUser));
        }
        return clientUserDtoList;
    }

    @Transactional
    public String addUser(String firstName, String lastName, String email, String password) {
        Optional<ClientUser> existingUser = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingUser.isPresent()) {
            return "Can't add new user";
        }
        ClientUser newUser = new ClientUser();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        clientUserRepository.save(newUser);
        return "New user was added";
    }
}
