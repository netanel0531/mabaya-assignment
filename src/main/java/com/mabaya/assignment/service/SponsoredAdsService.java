package com.mabaya.assignment.service;

import com.mabaya.assignment.model.Campaign;
import com.mabaya.assignment.model.Product;

public interface SponsoredAdsService {

    Campaign createCampaign(Campaign campaign);
    Product serverAd(String category);
}
