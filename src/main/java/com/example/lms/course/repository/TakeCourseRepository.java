package com.example.lms.course.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.course.domain.TakeCourse;

public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long>{

	long countByCourseIdAndUserIdAndStatusIn(long courseId, String userId, Collection<String> statusList);
}
