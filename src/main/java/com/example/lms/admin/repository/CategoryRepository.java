package com.example.lms.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.admin.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
