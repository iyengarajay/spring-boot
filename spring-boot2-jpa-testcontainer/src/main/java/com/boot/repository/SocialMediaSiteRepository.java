package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.SocialMediaSite;

public interface SocialMediaSiteRepository extends JpaRepository<SocialMediaSite, Long> {

	SocialMediaSite findByName(String name);

}
