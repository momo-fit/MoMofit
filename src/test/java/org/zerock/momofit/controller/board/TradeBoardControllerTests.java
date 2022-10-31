package org.zerock.momofit.controller.board;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//Spring Beans Container & DI 수행시키는 어노테이션
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/**/*-context.xml"
	})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TradeBoardControllerTests {
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.ctx);
		
		log.info("\t+ ctx: {}", this.ctx);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. TradeBoardController.list")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testList() throws Exception {
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/trade/list");
		requestBuilder.param("currPage", "1");
		requestBuilder.param("amount", "10");
		
		// Fluent-API 기반의 Method Chaining을 이용하여 간략하게 요청에 대한 응답결과 획득
		ModelAndView modelAndView = 
				mockMvc.
					perform(requestBuilder).
					andReturn().
					getModelAndView();

		
		log.info("\t+ modelAndView: {}", modelAndView);
		
	} // testList
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testGet")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	public void testGet() throws Exception {
		log.trace("testGet() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/get");
		reqBuilder.param("board_no", "84");
		reqBuilder.param("currPage", "3");
		
		ModelAndView modelAndView =
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView();
		
		log.info("\t+ modelAndView: {}", modelAndView);
	} // testGet
	
	
	

} // end class
