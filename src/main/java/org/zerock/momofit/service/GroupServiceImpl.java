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
<<<<<<< HEAD
	public GroupVO detail(Integer groupNo) {
		log.info("detail: {}", groupNo);
		
		return mapper.read(groupNo);
=======
	public GroupVO detail(Integer group_no) {
		log.info("detail: {}", group_no);
		
		return mapper.read(group_no);
>>>>>>> f82d7c7560e48ae27039d40d3049a6980c8da377
	}

	// 수정
	@Override
	public boolean modify(GroupVO group) {
		log.info("modify: {}", group);
		
		return mapper.update(group) == 1;
	}

	// 삭제
	@Override
<<<<<<< HEAD
	public boolean remove(Integer groupNo) {
		log.info("remove: {}", groupNo);
		
		return mapper.delete(groupNo) == 1;
=======
	public boolean remove(Integer group_no) {
		log.info("remove: {}", group_no);
		
		return mapper.delete(group_no) == 1;
>>>>>>> f82d7c7560e48ae27039d40d3049a6980c8da377
	}



}
