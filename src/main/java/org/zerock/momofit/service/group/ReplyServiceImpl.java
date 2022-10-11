package org.zerock.momofit.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.ReplyDTO;
import org.zerock.momofit.mapper.group.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    @Setter(onMethod_= @Autowired)
    private ReplyMapper mapper;
    
    // 1. 전체목록
    @Override
    public ReplyDTO get(Integer group_comm_no) {
        
        log.info("get....." + group_comm_no);
        
        return mapper.read(group_comm_no);
    }

    // 2. 댓글 등록
    @Override
    public int register(ReplyDTO rdto) {
        
        log.info("register....." + rdto);
        
        return mapper.insert(rdto);
    }
    
    // 3. 댓글 수정
    @Override
    public int modify(ReplyDTO rdto) {
        
        log.info("modify....." + rdto);
        
        return mapper.update(rdto);
    }

    // 4. 댓글 삭제
    @Override
    public int remove(Integer group_comm_no) {
        
        log.info("remove....." + group_comm_no);
        
        return mapper.delete(group_comm_no);
    }

    // 5. 페이징목록
    @Override
    public List<ReplyDTO> getList(Criteria cri, Integer group_no) {
        
        log.info("getList....." + group_no);
        
        return mapper.getListWithPaging(cri, group_no);
    };
}
