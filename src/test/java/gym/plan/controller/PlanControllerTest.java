package gym.plan.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import gym.plan.GymPlanApplication;
import gym.plan.controller.model.GymPlanData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = GymPlanApplication.class)
@ActiveProfiles("test")
//@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
//@SqlConfig(encoding = "utf-8")

class PlanControllerTest extends PlanServiceTestSupport {

	@Test
	void testInsertEntity() {
		//given: given a request
		GymPlanData request = buildInsertEvent(1);
		GymPlanData expected = buildInsertEvent(1);
		
		//when: the request is added to the table
		GymPlanData actual = insertEvent(request);
		System.out.println(actual);
		//then: the request is what is expected
		assertThat(actual).isEqualTo(expected);
		
		//and: there is one row in the table.
		assertThat(rowsInEventTable()).isOne();
	}

	@Test
	void testRetrieveEvent() {
		// given: a request
		GymPlanData event = insertEvent(buildInsertEvent(1));
		GymPlanData expected = buildInsertEvent(1);
		
		// when: the request is retrieved by ID
		GymPlanData actual = retrieveEventById(event.getEventId());
		
		// then: the actual request is equal to the expected request
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testRetrieveAllEvent() {
		// given: two requests 
		List<GymPlanData> expected = insertTwoEvents();
		
		// when: all requests are retrieved
		List<GymPlanData> actual = retrieveAllEvents();
		
		// then: the actual request is equal to the expected request
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testUpdateEvent() {
		//given: a request and an update
		insertEvent(buildInsertEvent(1));
		GymPlanData expected = buildUpdateEvent();
		
		//when: the request is updated
		GymPlanData actual = updateEvent(expected);
		
		//then: the request is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		//and: there is one row in the table
		assertThat(rowsInEventTable()).isOne();	
	}

}
