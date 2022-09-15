package org.zerock.momofit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
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
public class GroupServiceTests {

	@Setter(onMethod_= @Autowired)
	private GroupService service;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.service);
		log.info("\t+ this.service: {}", this.service);
	} // beforeAll
	
	
	// GroupService 객체가 제대로 주입이 가능한지 확인하는 작업으로 시작
	@Test
	@Order(1)
	@DisplayName("1. testExist")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	public void testExist()  {
		log.trace("testExist() invoked.");
		
		log.info(service);
		
		}
	
	// 등록 test
	
	@Test
	public void testRegister()  {
		log.trace("testRegister() invoked.");
		
		GroupVO group = new GroupVO();
		Date d = new Date();
		
		group.setGroup_name("새로 작성하는 모임 이름");
		group.setMember_max(10);
		group.setSchedule(d);
		group.setGroup_date(d);
		group.setGroup_like(30);
		group.setUser_no(1);
		group.setSports("헬스/크로스핏");
		
		service.register(group);
		
		log.info("생성된 게시물의 번호: " + group.getGroup_no());
		
		}

	
	// 목록 test
//	@Test
//	public void testGetList()  {
//		
//		service.getList().forEach(group -> log.info(group));
//		
//		}
	
	// 상세조회 test
//	@Test
//	public void testDetail()  {
//		
//		log.info(service.detail(5));
//		
//		}
	
	// 삭제 test
//	@Test
//	public void testDelete()  {
//		
//		log.info("REMOVE RESULT: " + service.remove(54));
//		
//		}
	
	// 수정 test
//	@Test
//	public void testUpdate()  {
//		
//		GroupVO group = service.detail(50);
//		
//		if (group == null) {
//			return;
//		} //if
//		
//		group.setGroup_name("제목 수정합니다.");
//		log.info("MODIFY RESULT: " + service.modify(group));
//		
//		}
	
	

	
	} // end class
	
