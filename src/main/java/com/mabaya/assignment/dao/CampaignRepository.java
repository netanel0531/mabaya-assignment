package com.mabaya.assignment.dao;

import com.mabaya.assignment.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

}