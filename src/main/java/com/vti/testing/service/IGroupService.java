package com.vti.testing.service;

import com.vti.testing.entity.Group;
import com.vti.testing.form.CreatingGroupForm;
import com.vti.testing.form.UpdatingGroupForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGroupService {
    Page<Group> getAllGroups(Pageable pageable);

    boolean createGroup(CreatingGroupForm groupForm);

    void updateGroupById(UpdatingGroupForm groupForm);

    void deleteGroupById(int id);

    Group findById(int id);

    Group getGroupByGroupName(String name);
}
