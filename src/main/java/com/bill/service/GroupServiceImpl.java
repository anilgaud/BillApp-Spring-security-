package com.bill.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.Dto.GroupDTO;
import com.bill.model.Group;
import com.bill.repository.GroupRepository;

@Service
public class GroupServiceImpl implements IGroupService {

	@Autowired
	GroupRepository groupRepository;

	@Override
	public void save(Group group) {

		groupRepository.save(group);
	}

	@Override
	public List<GroupDTO> getAllList() {
		List<Group> groupList=groupRepository.findAll();
		List<GroupDTO> groupDTOList=new ArrayList<GroupDTO>();
		groupList.forEach(group->{
			
			GroupDTO groupDTO=new GroupDTO();
			BeanUtils.copyProperties(group, groupDTO);
			groupDTOList.add(groupDTO);
			
		});
		
		return groupDTOList;
	}

	@Override
	public void deleteGroup(int id) {
		groupRepository.deleteById((long) id);

	}

	@Override
	public Group getgroupById(long id) {
		Optional<Group> group = groupRepository.findById(id);

		return group.get();
	}

	@Override
	public GroupDTO getGroupDetailsByGroupName(String groupname) {

		Group group=groupRepository.getGroupDetailsByGroupName(groupname);
		GroupDTO groupDTO=new GroupDTO();
		BeanUtils.copyProperties(group, groupDTO);
		return groupDTO;
	}

	@Override
	public void updateGroup(Group group) {
		groupRepository.updateGroup(group.getGroupname(), group.getMembername(), group.getId());

	}

	@Override
	public List<Map<String, Object>> report() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Group> groupList = groupRepository.findAll();
		for (Group group : groupList) {

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("Group Name", group.getGroupname());
			item.put("Member Names", group.getMembername());
			result.add(item);
		}
		return result;
	}

}
