package org.zerock.momofit.mapper.board;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
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
import org.zerock.momofit.domain.tradeboard.TradeBoardDTO;
import org.zerock.momofit.domain.tradeboard.TradeBoardVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.mapper.tradeboard.TradeBoardMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TradeBoardMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private TradeBoardMapper mapper;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.mapper);
		log.info("\t+ mapper:{}",this.mapper);
	} // beforeAll
	
	
	// 게시판 테이블의 전체목록 조회하기
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. BoardMapper.selectAllList test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testSelectAllList() throws DAOException{
		
		@Cleanup("clear")
		List<TradeBoardVO> list = this.mapper.selectAllList();
		
		list.forEach(log::info);
	} // testSelectAllList
	
	// 새로운 게시글을 등록하기
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. TradeBoardMapper.insert(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testInsert() throws DAOException {
		log.trace("testInsert() invoked.");
		
		
		TradeBoardDTO dto = new TradeBoardDTO();

		dto.setTitle("TITLE_NEW1234");
		dto.setText("Text_NEW1234");
		dto.setBoard_like(3);
		dto.setHits(1);
		dto.setImg_check(1);
		dto.setUser_no(1);
		dto.setCategory_no(2);
		dto.setSubject_no(null);		
		

		int affectedLines = this.mapper.insert(dto);
		log.info("\t+ affectedLines : {}", affectedLines);
	} // testInsert
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. TradeBoardMapper.insertSelectKey(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testInsertSelectKey() throws DAOException {
		log.trace("testInsertSelectKey() invoked.");
		
		TradeBoardDTO dto = new TradeBoardDTO();

		dto.setBoard_no(20);
		dto.setTitle("TITLE_NEW123");
		dto.setText("Text_NEW123");
		dto.setBoard_like(3);
		dto.setHits(1);
		dto.setImg_check(1);
		dto.setUser_no(1);
		dto.setCategory_no(2);
		dto.setSubject_no(null);	
		
		log.info("\t+ 1. dto: {}", dto);	
		log.info("\t+ 2. result : {}", this.mapper.insertSelectKey(dto) == 1);
		log.info("\t+ 3. dto: {}", dto);
	} // testInsertSelectKey
	
	
	// 기존 게시글 상세조회 하기
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. TradeBoardMapper.select(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testSelect() throws DAOException {
		log.trace("testSelect() invoked.");
		
		TradeBoardDTO dto = new TradeBoardDTO();
		dto.setBoard_no(3);
		
		log.info("\t+ TradeBoardVO : {}", this.mapper.select(dto));
	} // testSelect
	
	// 수정하기
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. TradeBoardMapper.update(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testUpdate() throws DAOException {
		log.trace("testUpdate() invoked.");
		
		TradeBoardDTO dto = new TradeBoardDTO();
		dto.setBoard_no(3);
		dto.setTitle("TITLE_UPDATED");
		dto.setText("TEXT_UPDATED");
		dto.setUser_no(10);


		log.info("\t+ result : {}", this.mapper.update(dto) == 1);
	} // testUpdate
	
	// 삭제하기 
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. TradeBoardMapper.delete(bno) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testDelete() throws DAOException {
		log.trace("testDelete() invoked.");
		
		log.info("\t+ result: {}", this.mapper.delete(300) == 1 );
	} // testDelete
	
	
} // end class
