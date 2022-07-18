package com.example.lms.banner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.banner.domain.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long>{
}
