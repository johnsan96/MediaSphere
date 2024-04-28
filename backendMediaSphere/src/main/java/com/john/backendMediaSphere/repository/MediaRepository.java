package com.john.backendMediaSphere.repository;

import com.john.backendMediaSphere.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository <Media, Long> {
}
