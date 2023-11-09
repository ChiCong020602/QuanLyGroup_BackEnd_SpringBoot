package com.vti.testing.controller;

import com.vti.testing.dto.UserDTO;
import com.vti.testing.entity.User;
import com.vti.testing.form.CreatingUserForm;
import com.vti.testing.form.EditProfileForm;
import com.vti.testing.form.UpdatePassForm;
import com.vti.testing.form.UpdatingUserForm;
import com.vti.testing.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper modelMapper;

    Random rand = new Random();

    @GetMapping
    public Page<UserDTO> getAllUsers(Pageable pageable){
        Page<User> userPage = userService.getAllUsers(pageable);
        List<User> users = userPage.getContent();
        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
        return new PageImpl<>(userDTOS, pageable, userPage.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreatingUserForm userForm){
        User user = userService.getUserByUserName(userForm.getUserName());
        User user1 = userService.getUserByEmail(userForm.getEmail());
        if(user == null && user1 == null){
            userService.createUser(userForm);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("not ok");
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> updatePassByUserName(@PathVariable String userName){
        User user = userService.getUserByUserName(userName);
        if(user != null){
            Integer randomPass = rand.nextInt(999999-100000)+100000;
            String newPass = randomPass.toString();
            user.setPassword(newPass);
            userService.updatePassByUserName(user);
            return ResponseEntity.ok(newPass);
        }
        return ResponseEntity.ok("not ok");
    }

    @GetMapping("/userName/{userName}")
    public UserDTO getUserByUserName(@PathVariable String userName){
        User user = userService.getUserByUserName(userName);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @PutMapping("/ChangePass/{userName}")
    public ResponseEntity<?> changePassByUserName(@PathVariable String userName, @RequestBody UpdatePassForm userForm){
        User user = userService.getUserByUserName(userName);
        if(userService.changePassByUserName(user, userForm)){
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("not ok");
    }

    @PutMapping("/EditProfile/{userName}")
    public ResponseEntity<?> editProfileByUserName(@PathVariable String userName, @RequestBody EditProfileForm profileForm){
        User user = userService.getUserByUserName(userName);
        if(userService.editProfileByUserName(user, profileForm)){
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("not ok");
    }
}
