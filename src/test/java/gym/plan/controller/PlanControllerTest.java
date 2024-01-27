package gym.plan.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import gym.plan.GymPlanApplication;
import gym.plan.controller.model.GymPlanData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = GymPlanApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")

class PlanControllerTest extends PlanServiceTestSupport {

	@Test
	void testInsetAndRetrieveEvent() {
		// given: a request
		GymPlanData expected = insertEvent(buildInsertEvent(1));
		
		// when: the request is retrieved by Name
		GymPlanData actual = retrieveEventByName(expected.getEventName());
		
		// then: the actual request is equal to the expected request
		assertThat(actual).isEqualTo(expected);
	}

	@Test 
	void testInsertAndRetrieveTwoEvent() {
		// given: two requests 
		List<GymPlanData> expected = insertTwoEvents();

		// when: all requests are retrieved
		List<GymPlanData> actual = retrieveTwoEventByName();

		// then: the actual request is equal to the expected request
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testUpdateAndRetrieveEvent() {
		//given: a request and an update
		GymPlanData original = insertEvent(buildInsertEvent(1));
		GymPlanData request = buildUpdateEvent();
		GymPlanData expected = updateEvent(buildUpdateEvent());
		
		//when: the request is updated
		GymPlanData actual = retrieveEventByName(request.getEventName());
		
		//then: the request is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		//and: that actual is not equal to original row in the table
		assertThat(actual).isNotEqualTo(original);	
	}
}
