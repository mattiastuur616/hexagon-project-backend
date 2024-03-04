package ee.hexagon.hexagonprojectbackend.controller;

import ee.hexagon.hexagonprojectbackend.dto.ClientUserDto;
import ee.hexagon.hexagonprojectbackend.service.ClientUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@RestController
public class ClientUserController {
    private final ClientUserService clientUserService;

    @GetMapping("/allUsers")
    public List<ClientUserDto> getAllUsers() {
        return clientUserService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName,
                                   @RequestParam String email, @RequestParam String password) {
        return clientUserService.addUser(firstName, lastName, email, password);
    }
}
