package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.SystemAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SystemAssetRepository extends JpaRepository<SystemAsset, Long> {
    List<SystemAsset> findByStatus(String status);
}
