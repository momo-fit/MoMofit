package org.zerock.momofit.service;

import java.util.List;

import org.zerock.momofit.domain.GroupVO;

public interface GroupService {

	public void register(GroupVO group);
	public GroupVO detail(Integer group_no);
	public boolean modify(GroupVO group);
	public boolean remove(Integer group_no);
	public List<GroupVO> getList();
}
