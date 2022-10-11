package org.zerock.momofit.service.group;

import java.util.List;

import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.ReplyDTO;

public interface ReplyService {

	// 1. 전체목록
	public ReplyDTO get(Integer group_comm_no);
	
	// 2. 댓글 등록
	public int register(ReplyDTO rdto);

	// 3. 댓글 수정
	public int modify(ReplyDTO rdto); 
	
	// 4. 댓글 삭제
	public int remove(Integer group_comm_no);
	
	// 5. 페이징목록
	public List<ReplyDTO> getList(Criteria cri, Integer group_no);

} // end
