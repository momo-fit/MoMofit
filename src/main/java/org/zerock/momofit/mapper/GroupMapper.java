package org.zerock.momofit.mapper;

import java.util.List;

import org.zerock.momofit.domain.GroupVO;


public interface GroupMapper {

	// 1. 게시판 테이블의 전체목록 조회하기
	
//	@Select("select * from GROUPS where GROUP_NO > 0")
	public abstract List<GroupVO> getList();
	
	// 2. 새로운 게시글을 등록하기(CREATE) - Mapper XML 파일로 처리
	public void insert(GroupVO group);
	public void insertSelectKey(GroupVO group);		// *** : 새로 입력된 게시글의 bno를 반환받기를 원하는 경우
	
	// 3. 상세조회 (read)
	public GroupVO read(Integer group_no);
	
	// 4. 삭제 (delete)
	public int delete(Integer group_no);		
	
	// 5. 수정 (update)
	public int update(GroupVO group);		// 삭제/수정 여부 -> 0 or 1 ( int타입으로 설계 )
	
	
}
