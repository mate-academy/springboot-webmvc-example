package mate.academy.resttesting.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.resttesting.model.User;
import mate.academy.resttesting.model.UserDto;
import mate.academy.resttesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> dtos = userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
