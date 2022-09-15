package org.zerock.momofit.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.SelectKey;
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
import org.zerock.momofit.domain.GroupVO;

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
	
	
//	@Test
//	@Order(1)
//	public void testGroupMapper()  {
//		log.trace("testGroupMapper() invoked.");
//		
//		@Cleanup("clear")
//		List<GroupVO> list = this.mapper.getList();
//		
//		for(GroupVO vo : list ) {
//			log.info("\t+ vo : {}", vo);
//		} // enhanced for
//	}
	
//	@Test
//	@Order(1)
//	public void testInsert()  {
//		log.trace("testInsert() invoked.");
//		
//		GroupVO group = new GroupVO();
//		
//		Date d = new Date();
//		
//		group.setGroup_name("새로 작성하는 모임 이름");
//		group.setMember_max(10);
//		group.setSchedule(d);
//		group.setGroup_date(d);
//		group.setGroup_like(30);
//		group.setUser_no(1);
//		
//
//		mapper.insert(group);
//		
//		log.info(group);
//		} 
	
//	@Test
//	@Order(1)
//	public void testRead()  {
//		log.trace("testRead() invoked.");
//		
//		// 존재하는 게시물 번호로 테스트
//		GroupVO group = mapper.read(5);
//		
//		log.info(group);
//	}
	
//	@Test
//	@Order(1)
//	public void testDelete()  {
//		log.trace("testDelete() invoked.");
//		
//		// 존재하는 게시물 번호로 테스트 --> 1 반환	
//		log.info("DELETE COUNT: " + mapper.delete(53));
//		
//		// 존재하지 않는 게시물 번호로 테스트 --> 0 반환	
//		log.info("DELETE COUNT: " + mapper.delete(5000));
//	}
	
	@Test
	public void testUpdate()  {
		log.trace("testUpdate() invoked.");
		
		GroupVO group = new GroupVO();
		
		Date d = new Date();
		
		// 실행 전 존재하는 번호인지 확인할 것
		
		group.setGroup_no(56);
		group.setGroup_name("수정된 모임 이름");
		group.setMember_max(8);
		group.setSchedule(d);
		
		int count = mapper.update(group);
		log.info("UPDATE COUNT: " + count);

	}
	
	
	
}
