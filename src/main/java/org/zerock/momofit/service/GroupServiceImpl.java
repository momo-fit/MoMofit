package org.zerock.momofit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.GroupVO;
import org.zerock.momofit.mapper.GroupMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor

public class GroupServiceImpl implements GroupService {
	
	@Setter(onMethod_= @Autowired)
	private GroupMapper mapper;

	// 등록
	@Override
	public void register(GroupVO group) {
		
		log.info("register: {}", group);
		
		mapper.insertSelectKey(group);
		
	}

	// 목록
	@Override
	public List<GroupVO> getList() {
		log.info("getList...");
		
		return mapper.getList();
	}
	
	// 조회
	@Override
	public GroupVO detail(Integer group_no) {
		log.info("detail: {}", group_no);
		
		return mapper.read(group_no);
	}

	// 수정
	@Override
	public boolean modify(GroupVO group) {
		log.info("modify: {}", group);
		
		return mapper.update(group) == 1;
	}

	// 삭제
	@Override
	public boolean remove(Integer group_no) {
		log.info("remove: {}", group_no);
		
		return mapper.delete(group_no) == 1;
	}



}
