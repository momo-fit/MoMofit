package org.zerock.momofit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.service.group.GroupService;

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
	
	// 목록 test
	@Disabled
	@Test
	public void testGetList() throws ServiceException, DAOException  {
		
		service.getList().forEach(group -> log.info(group));
		
		}	
	
	// 페이징된 목록 test	
	@Disabled
	@Test
	public void TestgetListPerPage() throws ServiceException, DAOException  {
		log.trace("TestgetListPerPage() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(5);
		cri.setAmount(20);
		 
		service.getListPerPage(cri).forEach(group -> log.info(group));
		
		}
	
	// 게시글 총 개수 반환 test
	@Disabled
	@Test
	public void TestGetTotal() throws ServiceException, DAOException  {
		
		log.trace("TestGetTotal() invoked.");
		 
		log.info("\t+ total: {}", this.service.getTotal());
		
		}
	
	// 등록 test
	
	@Disabled
	@Test
	public void testRegister() throws ServiceException, DAOException  {
		log.trace("testRegister() invoked.");
		
		GroupDTO dto = new GroupDTO();
		Date d = new Date();
		
		dto.setGroup_name("새로 작성하는 모임 이름");
		dto.setMember_max(10);
		dto.setSchedule(d);
		dto.setGroup_date(d);
//		dto.setGroup_like(32);
//		dto.setUser_no(1);
		dto.setSports("헬스/크로스핏");
		
		service.register(dto);
		
		log.info("생성된 게시물의 번호: " + dto.getGroup_no());
		
		}

	
	// 상세조회 test
	@Disabled
	@Test
	public void testDetail() throws ServiceException, DAOException  {
		
		log.info(service.detail(5));
		
		}
	
	// 삭제 test
	@Disabled
	@Test
	public void testDelete() throws ServiceException, DAOException  {
		
		log.info("REMOVE RESULT: " + service.remove(106));
		
		}
	
	// 수정 test
	@Disabled
	@Test
	public void testUpdate() throws ServiceException, DAOException  {
		
		GroupDTO dto = service.detail(59);
		
		if (dto == null) {
			return;
		} //if
		
		// 모임이름 변경으로 test
		dto.setGroup_name("수정test");
		log.info("MODIFY RESULT: " + service.modify(dto));
		
		}
	
	} // end class
	
