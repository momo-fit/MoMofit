package org.zerock.momofit.service.mypage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.momofit.domain.mypage.Criteria;
import org.zerock.momofit.domain.mypage.MyGroupVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.mypage.MyGroupMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Service
public class MyGroupServiceImpl implements MyGroupService {

	private MyGroupMapper myGroupMapper;
	
	// 1. 내가 참여한 Group List 
	@Override
	public List<MyGroupVO> getGroupList(Criteria cri) throws ServiceException {
		log.trace("getGroupList() invoked.");
		
		try {
			
			return this.myGroupMapper.getGroupListWithPaging(cri);
			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getGroupList

	@Override
	public int getGroupCount(int user_no) throws ServiceException {
		log.trace("getCroupCount() invoked.");
		
		try {
			
			return this.myGroupMapper.getMyGroupCount(user_no);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // getGroupCount

	// 모임나가기
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean quitMyGroup(int group_no, int user_no) throws ServiceException {
		log.trace("quitMyGroup() invoked.");
		
		try {
			
			this.myGroupMapper.updateGroupMemberCnt(group_no, -1);
			
			return this.myGroupMapper.quitGroup(group_no, user_no) == 1;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	} // getGroupCount

	// 모임장 번호와 요청한 유저번호가 동일한지 판단
	@Override
	public boolean isQuitGroup(int group_no, int user_no) throws ServiceException {
		log.trace("isQuitGroup() invoked.");

		try {
			
			int group_admin_no = this.myGroupMapper.getGroupAdminNo(group_no);
			
			return user_no == group_admin_no;
	
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // isQuitGroup
	
} // end class
