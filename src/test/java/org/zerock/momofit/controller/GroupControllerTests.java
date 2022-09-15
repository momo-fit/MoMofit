package org.zerock.momofit.controller;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.momofit.controller.group.GroupController;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// For JUnit Jupyter 5
@ExtendWith(SpringExtension.class)

// Test for Controller
@WebAppConfiguration

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupControllerTests {


	@Setter(onMethod_= {@Autowired})
	private GroupController controller;
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.ctx);
		log.info("\t+ mapper: {}", this.ctx);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. GroupController.list")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void testList() throws Exception {
		log.trace("testList() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/group/list");
				
		// Fluent-API 기반의 Method Chaining을 이용하여 간략하게 요청에 대한 응답결과 획득
		ModelAndView modelAndView =
		mockMvc.
			perform(requestBuilder).	
			andReturn().				
			getModelAndView();
		
		log.info("\t+ modelAndView: {}", modelAndView);
		
	}
	
}