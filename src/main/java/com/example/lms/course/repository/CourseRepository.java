package com.example.lms.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.course.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
