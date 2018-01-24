package com.mumscheduler.course.repository;

import com.mumscheduler.course.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	/**
	 * Get a course by code
	 */
	@Query("FROM Course c WHERE c.code=:code")
	public Course getCourseByCode(@Param("code") String code);
	
}
