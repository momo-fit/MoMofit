package org.zerock.momofit.mapper.group;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.ReplyDTO;

public interface ReplyMapper {
    /* 등록 */
    public int insert(ReplyDTO rdto);
    
    /* 조회 */
    public ReplyDTO read(int group_no);
    
    /* 삭제 */
    public int delete(int group_no);
    
    /* 수정 */
    public int update(ReplyDTO rdto);
    
    /* 페이징된 목록 */
    public List<ReplyDTO> getListWithPaging(
            @Param("cri") Criteria cri,
            @Param("group_no") Integer group_no);
}
