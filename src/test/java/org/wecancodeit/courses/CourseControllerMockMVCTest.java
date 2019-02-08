package org.wecancodeit.courses;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;



import java.util.Collection;



@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)

public class CourseControllerMockMVCTest {

	@Resource
	private MockMvc mvc;
	
	@Mock
	private Course firstCourse;
	
	@Mock
	private Course secondCourse;
	
	@MockBean
	private CourseRepository repository;
	
	
	@Test
	public void should_Be_Okay_For_All_Courses() throws Exception {
		mvc.perform(get("/show-courses")).andExpect(status().isOk());
	}
	
	@Test
	public void should_Route_To_All_Courses_View() throws Exception {
		mvc.perform(get("/show-courses")).andExpect(view().name(is("courses")));
	}
	
	@Test
	public void should_Put_All_Courses_Into_Model() throws Exception {
		Collection<Course>allCourses = asList(firstCourse, secondCourse);
		when(repository.findAll()).thenReturn(allCourses);
		mvc.perform(get("/show-courses")).andExpect(model().attribute("courses", is(allCourses)));
	}
	
	@Test
	public void should_Be_Ok_For_Single_Course() throws Exception {
		mvc.perform(get("/course?id=1")).andExpect(status().isOk());
	}
	
	
	@Test
	public void should_Route_To_Single_Course() throws Exception {
		mvc.perform(get("/course?id=1")).andExpect(view().name(is("course")));
	}
	
	@Test
	public void should_Put_A_Single_Course_Into_Model() throws Exception {
		when(repository.findOne(1L)).thenReturn(firstCourse);
		mvc.perform(get("/course?id=1")).andExpect(model().attribute("courses", is(firstCourse)));
	}
	
	
	
	
	
	
}
