package com.vti.testing.service;

import com.vti.testing.entity.Group;
import com.vti.testing.form.CreatingGroupForm;
import com.vti.testing.form.UpdatingGroupForm;
import com.vti.testing.reponsitory.IGroupReponsitory;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupService implements IGroupService{
    @Autowired
    private IGroupReponsitory groupReponsitory;

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Group> getAllGroups(Pageable pageable) {
        return groupReponsitory.findAll(pageable);
    }

    @Override
    public boolean createGroup(CreatingGroupForm groupForm) {
        Group groupCheck = groupReponsitory.findByGroupName(groupForm.getGroupName());
        if(groupCheck == null){
            TypeMap<CreatingGroupForm, Group> typeMap = modelMapper.getTypeMap(CreatingGroupForm.class, Group.class);
            if(typeMap == null){
                modelMapper.addMappings(new PropertyMap<CreatingGroupForm, Group>() {
                    @Override
                    protected void configure(){
                        skip(destination.getId());
                    }
                });
            }
            Group group = modelMapper.map(groupForm, Group.class);
            group.setCreator(userService.getUserById(groupForm.getCreatorUserId()));
            groupReponsitory.save(group);
            return true;
        }
        return false;
    }

    @Override
    public void updateGroupById(UpdatingGroupForm groupForm) {
        Group group = groupReponsitory.findById(groupForm.getId()).get();
        group.setGroupName(groupForm.getGroupName());
        group.setMember(groupForm.getMember());
        groupReponsitory.save(group);
    }

    @Override
    public void deleteGroupById(int id) {
        groupReponsitory.deleteById(id);
    }

    @Override
    public Group findById(int id) {
        return groupReponsitory.findById(id).get();
    }

    @Override
    public Group getGroupByGroupName(String name) {
        return groupReponsitory.findByGroupName(name);
    }

}
