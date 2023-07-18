package com.mabaya.assignment.dao;

import com.mabaya.assignment.model.CampaignProduct;
import com.mabaya.assignment.model.CampaignProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CampaignProductRepository extends JpaRepository<CampaignProduct, CampaignProductId> {
    @Query(value = "SELECT * FROM Campaign_Product cp " +
            "JOIN (" +
            "    SELECT MAX(cp1.bid) AS max_bid" +
            "    FROM Campaign_Product cp1" +
            "    WHERE cp1.category = ?1" +
            ") AS maxcp ON cp.bid = maxcp.max_bid " +
            "WHERE cp.category = ?1 " +
            "LIMIT 1", nativeQuery = true)
    Optional<CampaignProduct> findAnyByCategoryByMaxBid(String category);


    @Query(value = "SELECT * FROM Campaign_Product cp WHERE cp.bid = (SELECT MAX(cp1.bid) FROM Campaign_Product cp1) LIMIT 1", nativeQuery = true)
    Optional<CampaignProduct> findAnyByMaxBid();

}
