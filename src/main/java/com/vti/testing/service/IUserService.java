package com.vti.testing.service;

import com.vti.testing.entity.User;
import com.vti.testing.form.CreatingUserForm;
import com.vti.testing.form.EditProfileForm;
import com.vti.testing.form.UpdatePassForm;
import com.vti.testing.form.UpdatingUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    Page<User> getAllUsers(Pageable pageable);

    void createUser(CreatingUserForm userForm);

    void updatePassByUserName(User user);

    User getUserByUserName(String name);

    User getUserById(int creatorUserId);

    boolean changePassByUserName(User user, UpdatePassForm userForm);

    boolean editProfileByUserName(User user, EditProfileForm profileForm);

    User getUserByEmail(String email);
}
