package org.zerock.momofit.service.group;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.group.GroupMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor

public class GroupServiceImpl implements GroupService  {

	@Setter(onMethod_= @Autowired)
	private GroupMapper mapper;

	// 1. 전체목록
	@Override
	public List<GroupVO> getList() throws DAOException {
		log.info("getList...");

		return mapper.getList();
	}
	
	// 1-2. 페이징 처리된 게시글 목록
	@Override
	public List<GroupVO> getListPerPage(Criteria cri) throws ServiceException {
		log.trace("getListPerPages({}) invoked.", cri);
		
		try {
			
			return this.mapper.getListWithPaging(cri);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // getListPerPages
	
	// 1-3. Best 글 (좋아요순) 목록
	public List<GroupVO> likeSelect() {
		
		return mapper.likeSelect();
		
	}
	
	
	// 2. 새로운 게시글 등록
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean register(GroupDTO dto) throws ServiceException {

		log.info("register..." + dto);
		try {
			
			String ttt = dto.getGroup_loc();
			String loc = ttt.replace("," , " ");
			dto.setGroup_loc(loc);
			
			// Step.1 : Group생성
			mapper.insertSelectKey(dto);
			
			// Step.2 : Step.1로부터 Group_no획득
			int group_no = dto.getGroup_no();
			log.info("\t+ group_no : {}", group_no);
			int user_no = dto.getUser_no();
			log.info("\t+ useR_no : {},", user_no);
			
			// Step.3 : Group_member(그룹에 속한 사용자) 등록하기
			return this.mapper.joinGroup(group_no, user_no) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	// 2-2. 이미지 데이터 반환
	public GroupDTO getAttach(Integer group_no) {
		
		log.info("getAttachList.........");
		
		return mapper.getAttach(group_no);
	}
	
	// 3. 특정 게시글 상세조회
	@Override
	public GroupDTO detail(Integer group_no) throws DAOException {
		log.info("detail: {}", group_no);

		return mapper.read(group_no);
	}

	// 4. 기존 게시글 수정
	@Override
	public boolean modify(GroupDTO dto) throws DAOException {
		log.info("modify: {}", dto);
		
		String ttt = dto.getGroup_loc();
		String loc = ttt.replace("," , " ");
		dto.setGroup_loc(loc);

		return mapper.update(dto) == 1;
	}

	// 5. 기존 게시글 삭제
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(Integer group_no) throws ServiceException {
		log.info("remove: {}", group_no);

		try {
			mapper.delete(group_no);
		
			return mapper.deleteGroupMember(group_no) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	} // remove

	// 6. 총 게시글 개수 조회	
	@Override
	public int getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");
		
		try {
			
			return this.mapper.getTotalCount();
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // getTotal

	
} // end class