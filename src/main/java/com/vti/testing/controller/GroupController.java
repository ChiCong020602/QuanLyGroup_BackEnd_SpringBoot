package com.vti.testing.controller;

import com.vti.testing.dto.GroupDTO;
import com.vti.testing.entity.Group;
import com.vti.testing.form.CreatingGroupForm;
import com.vti.testing.form.UpdatingGroupForm;
import com.vti.testing.service.IGroupService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@CrossOrigin("*")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<GroupDTO> getAllGroups(Pageable pageable){
        Page<Group> groupPage = groupService.getAllGroups(pageable);
        List<Group> groups = groupPage.getContent();
        List<GroupDTO> groupDTOS = modelMapper.map(groups, new TypeToken<List<GroupDTO>>(){}.getType());
        return new PageImpl<>(groupDTOS, pageable, groupPage.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody CreatingGroupForm groupForm){
        if(groupService.createGroup(groupForm)) {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.ok("not ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroupById(@PathVariable int id, @RequestBody UpdatingGroupForm groupForm){
        groupForm.setId(id);
        groupService.updateGroupById(groupForm);

        return ResponseEntity.ok(modelMapper.map(groupService.findById(id), GroupDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupById(@PathVariable int id){
        groupService.deleteGroupById(id);

        return ResponseEntity.ok(id);
    }

    @GetMapping("/name/{name}")
    public GroupDTO getGroupByGroupName(@PathVariable String name){
        Group group = groupService.getGroupByGroupName(name);
        GroupDTO groupDTO = modelMapper.map(group, GroupDTO.class);
        return groupDTO;
    }
}
