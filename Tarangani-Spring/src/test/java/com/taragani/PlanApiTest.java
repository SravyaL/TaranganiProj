package com.taragani;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.convert.ThreeTenBackPortConverters.LocalDateTimeToJavaTimeInstantConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.taragani.TestUtil.TestUtil;
import com.taragani.model.Plan;
import com.taragani.service.PlanApi;
import com.taragani.service.PlanService;




@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PlanApi.class)

public class PlanApiTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private PlanService planServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllPlans() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(planList);

		mockMvc.perform(get("/plans")).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void testGetPlanBypTitle() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		Plan planExists = new Plan();
		String pTitle="Plan1";

		when(planServiceMock.getPlanBypTitle(pTitle)).thenReturn(planExists);

		mockMvc.perform(get("/plans/Plan1")).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void testGetPlansBySpeed() throws Exception {
		assertThat(this.planServiceMock).isNotNull();
		int pSpeed = 40 ;
	

		List<Plan> planAdded = new ArrayList<>();
		planAdded.add(new Plan());

		
		when(planServiceMock.getPlansBySpeed(pSpeed)).thenReturn(planAdded);

		mockMvc.perform(get("/plans/speed/40")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testGetPlansByUsage() throws Exception {
		assertThat(this.planServiceMock).isNotNull();
		int pUsage = 250 ;
	

		List<Plan> planAdded = new ArrayList<>();
		planAdded.add(new Plan());

		
		when(planServiceMock.getPlansByUsage(pUsage)).thenReturn(planAdded);

		mockMvc.perform(get("/plans/maxUsage/40")).andExpect(status().isOk()).andDo(print());

	}



}
