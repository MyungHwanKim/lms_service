package com.example.lms.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.course.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	Optional<List<Course>> findByCategoryId(long categoryId);
}
