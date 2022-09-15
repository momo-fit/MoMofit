package org.zerock.momofit.service;

import java.util.List;

import org.zerock.momofit.domain.GroupVO;

public interface GroupService {

	public void register(GroupVO group);
<<<<<<< HEAD
	public GroupVO detail(Integer groupNo);
	public boolean modify(GroupVO group);
	public boolean remove(Integer groupNo);
=======
	public GroupVO detail(Integer group_no);
	public boolean modify(GroupVO group);
	public boolean remove(Integer group_no);
>>>>>>> f82d7c7560e48ae27039d40d3049a6980c8da377
	public List<GroupVO> getList();
}
