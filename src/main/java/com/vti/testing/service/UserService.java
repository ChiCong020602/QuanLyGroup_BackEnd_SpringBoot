package com.vti.testing.service;

import com.vti.testing.entity.User;
import com.vti.testing.form.CreatingUserForm;
import com.vti.testing.form.EditProfileForm;
import com.vti.testing.form.UpdatePassForm;
import com.vti.testing.form.UpdatingUserForm;
import com.vti.testing.reponsitory.IUserReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserReponsitory userReponsitory;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userReponsitory.findAll(pageable);
    }

    @Override
    public void createUser(CreatingUserForm userForm) {
        userForm.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        User user = modelMapper.map(userForm, User.class);
        userReponsitory.save(user);
    }

    @Override
    public void updatePassByUserName(User user) {
        User nuser = userReponsitory.findByUserName(user.getUserName());
        nuser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        nuser = modelMapper.map(user, User.class);
        userReponsitory.save(nuser);
    }

    @Override
    public User getUserByUserName(String name) {
        return userReponsitory.findByUserName(name);
    }

    @Override
    public User getUserById(int id) {
        return userReponsitory.findById(id).get();
    }

    @Override
    public boolean changePassByUserName(User user, UpdatePassForm userForm) {
        if(BCrypt.checkpw(userForm.getOldPass(), user.getPassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(userForm.getNewPass()));
            userReponsitory.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean editProfileByUserName(User user, EditProfileForm profileForm) {
//        System.out.println(profileForm.getTypeEProfile());
//        System.out.println(profileForm.getElmentProfile());
        if((profileForm.getTypeEProfile()).equals("email")){
            User user1 = userReponsitory.findByEmail(profileForm.getElmentProfile());
            if(user1 != null){
                return false;
            }
            user.setEmail(profileForm.getElmentProfile());
        }else if((profileForm.getTypeEProfile()).equals("name")){
            String name = profileForm.getElmentProfile();
            name = name.trim();
            name = name.replaceAll("\\s{2,}", " ");
            String s[] = name.split(" ");
            String firstName = s[s.length-1];
            String lastName = "";
            for(int i = 0; i<s.length-1; i++){
                lastName =  lastName + s[i]+" ";
            }
            lastName = lastName.trim();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userReponsitory.save(user);
        }
        userReponsitory.save(user);
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        return userReponsitory.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReponsitory.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.createAuthorityList("user"));
    }
}
