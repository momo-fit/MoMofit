package org.zerock.momofit.mapper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.mapper.group.GroupMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupMapperTests {

	@Setter(onMethod_= @Autowired)
	private GroupMapper mapper;
	
	
	@Disabled
	@Test
	@Order(1)
	public void testGroupMapper() throws DAOException  {
		log.trace("testGroupMapper() invoked.");
		
		@Cleanup("clear")
		List<GroupVO> list = this.mapper.getList();
		
		for(GroupVO vo : list ) {
			log.info("\t+ vo : {}", vo);
		} // enhanced for
	}
	
	
	// ** 페이징 test **
	
	@Disabled
	@Test
	@Order(1)
	public void testGetListWithPaging() throws DAOException  {
		log.trace("testGetListWithPaging() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);
		cri.setAmount(16);
		
		@Cleanup("clear")
		List<GroupVO> list = this.mapper.getListWithPaging(cri);
			
		list.forEach(log::info);
		
//		for(GroupVO vo : list ) {
//			log.info("\t+ vo : {}", vo);
//		} // enhanced for
	}
	
	
	// ** 검색 test **
	
//	@Disabled
	@Test
	@Order(1)
	public void testSearch() throws DAOException  {
		log.trace("testGetListWithPaging() invoked.");
		
		Criteria cri = new Criteria();
		cri.setKeyword("강남구");
//		cri.setType("C");
		
		List<GroupVO> list = this.mapper.getListWithPaging(cri);
			
		list.forEach(log::info);
		
	}

	
	@Disabled
	@Test
	@Order(1)
	public void testInsert() throws DAOException  {
		log.trace("testInsert() invoked.");
		
		GroupDTO dto = new GroupDTO();
		
		Date d = new Date();
		
		dto.setGroup_name("새로 작성하는 모임 이름");
		dto.setMember_max(10);
		dto.setSchedule(d);
		dto.setGroup_date(d);
//		dto.setGroup_like(30);
//		dto.setUser_no(1);
		

		mapper.insert(dto);
		
		log.info(dto);
		} 
	
	@Disabled
	@Test
	@Order(1)
	public void testRead() throws DAOException  {
		log.trace("testRead() invoked.");
		
		// 존재하는 게시물 번호로 테스트
		GroupDTO group = mapper.read(172);
		
		log.info(group);
	}
	
	/*이미지 정보 반환*/
	@Test
	public void getAttachListTests() {
		log.trace("getAttachListTests() invoked.");
		int group_no = 173;
		
		log.info("이미지 정보 : " + mapper.getAttach(group_no));
			
	}
	
	
	@Disabled
	@Test
	@Order(1)
	public void testDelete() throws DAOException  {
		log.trace("testDelete() invoked.");
		
		// 존재하는 게시물 번호로 테스트 --> 1 반환	
		log.info("DELETE COUNT: " + mapper.delete(53));
		
		// 존재하지 않는 게시물 번호로 테스트 --> 0 반환	
		log.info("DELETE COUNT: " + mapper.delete(5000));
	}
	
	@Disabled
	@Test
	public void testUpdate() throws DAOException  {
		log.trace("testUpdate() invoked.");
		
		GroupDTO dto = new GroupDTO();
		
		Date d = new Date();
		
		// 실행 전 존재하는 번호인지 확인할 것
		
		dto.setGroup_no(56);
		dto.setGroup_name("수정된 모임 이름");
		dto.setMember_max(8);
		dto.setSchedule(d);
		
		int count = mapper.update(dto);
		log.info("UPDATE COUNT: " + count);

	}
	
		
}
