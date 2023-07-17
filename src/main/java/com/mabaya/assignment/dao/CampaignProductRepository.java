package com.mabaya.assignment.dao;

import com.mabaya.assignment.model.CampaignProduct;
import com.mabaya.assignment.model.CampaignProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignProductRepository extends JpaRepository<CampaignProduct, CampaignProductId> {
    @Query("SELECT cp FROM CampaignProduct cp " +
            "JOIN (" +
            "    SELECT MAX(cp1.bid) AS max_bid" +
            "    FROM CampaignProduct cp1" +
            "    WHERE cp1.category = ?1" +
            ") AS maxcp ON cp.bid = maxcp.max_bid " +
            "WHERE cp.category = ?1")
    List<CampaignProduct> findByCategoryByMaxBid(String category);


    @Query("SELECT cp FROM CampaignProduct cp WHERE cp.bid = (SELECT MAX(cp1.bid) FROM CampaignProduct cp1)")
    List<CampaignProduct> findByMaxBid();

}
