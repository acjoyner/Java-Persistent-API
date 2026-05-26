package com.anthony.coding.jpa.repositories;


import com.anthony.coding.jpa.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {

}
