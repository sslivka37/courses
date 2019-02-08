package org.wecancodeit.courses;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class CoursesRepositoryTest {
	
	
	CourseRepository underTest;
	private long firstCourseId = 1L;

	private Course firstCourse = new Course(firstCourseId, "course name", "course description");
	private long secondCourseId = 2L;
	private  Course secondCourse = new Course(secondCourseId, "course name", "course description");
	
	@Test
	public void should_Find_A_Course() {
		CourseRepository underTest = new CourseRepository(firstCourse);
		Course result = underTest.findOne(firstCourseId);
		assertThat(result, is(firstCourse));
	}
	
	@Test
	public void should_Find_A_Second_Course() {
		CourseRepository underTest = new CourseRepository(secondCourse);
		Course result = underTest.findOne(secondCourseId);
		assertThat(result, is(secondCourse));
	}
	
	@Test
	public void should_Find_All_Courses() {
		underTest = new CourseRepository(firstCourse, secondCourse);
		Collection<Course> result = underTest.findAll();
		assertEquals(2, result.size());
	}

}
