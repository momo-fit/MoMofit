package org.zerock.momofit.service;

import java.util.List;

import org.zerock.momofit.domain.GroupVO;

public interface GroupService {

	public void register(GroupVO group);
	public GroupVO detail(Integer groupNo);
	public boolean modify(GroupVO group);
	public boolean remove(Integer groupNo);
	public List<GroupVO> getList();
}
