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
import java.util.Objects;
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
        } else if (Objects.equals(firstName, "") || Objects.equals(lastName, "")
                || Objects.equals(email, "") || Objects.equals(password, "")) {
            return "One or multiple values are empty";
        } else if (Objects.equals(password, password.toLowerCase()) || password.length() < 18) {
            return "Invalid password";
        }
        ClientUser newUser = new ClientUser();
        newUser.setFirstName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1));
        newUser.setLastName(lastName.substring(0, 1).toUpperCase() + lastName.substring(1));
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        clientUserRepository.save(newUser);
        return "New user was added";
    }

    @Transactional
    public boolean login(String email, String password) {
        Optional<ClientUser> existingUser = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingUser.isEmpty()) {
            return false;
        }
        ClientUser clientUser = existingUser.get();
        return passwordEncoder.matches(password, clientUser.getPassword());
    }

    @Transactional
    public String getUserName(String email) {
        Optional<ClientUser> existingUser = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingUser.isEmpty()) {
            return "";
        }
        ClientUser clientUser = existingUser.get();
        return clientUser.getFirstName() + " " + clientUser.getLastName();
    }
}
