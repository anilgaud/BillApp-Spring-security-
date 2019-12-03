package com.bill.service;

import java.util.List;
import java.util.Map;

import com.bill.Dto.GroupDTO;
import com.bill.model.Group;

public interface IGroupService {

	public void save(Group group);

	public List<GroupDTO> getAllList();

	public void deleteGroup(int id);

	Group getgroupById(long id);

	GroupDTO getGroupDetailsByGroupName(String groupname);
	
	void updateGroup(Group group);
	
	public List<Map<String,Object>> report();

}
