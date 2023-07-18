package com.mabaya.assignment.service;

import com.mabaya.assignment.dao.CampaignProductRepository;
import com.mabaya.assignment.dao.CampaignRepository;
import com.mabaya.assignment.dao.ProductRepository;
import com.mabaya.assignment.model.Campaign;
import com.mabaya.assignment.model.CampaignProduct;
import com.mabaya.assignment.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class SponsoredAdsServiceImpl implements SponsoredAdsService {

    private CampaignRepository campaignRepository;
    private ProductRepository productRepository;
    private CampaignProductRepository campaignProductRepository;

    @Value("${campaign.active.days}") // = Duration.ofDays(10);
    public int CAMPAIGN_TTL_DAYS;


    @Autowired
    public SponsoredAdsServiceImpl(CampaignRepository campaignRepository, ProductRepository productRepository,
                                   CampaignProductRepository campaignProductRepository) {
        this.campaignRepository = campaignRepository;
        this.productRepository = productRepository;
        this.campaignProductRepository = campaignProductRepository;
    }
    @Override
    public Campaign createCampaign(Campaign campaign) {

        //Save the new campaign and return it if succeed
        Campaign newCampaign = Campaign.builder().bid(campaign.getBid())
                .name(campaign.getName()).startDate(campaign.getStartDate())
                .productIdentifierToPromote(campaign.getProductIdentifierToPromote())
                .build();

        campaignRepository.saveAndFlush(newCampaign);

        // Update the Campaign-Product table.
        campaign.getProductIdentifierToPromote().forEach(productId ->
                productRepository.findById(productId).ifPresent(product ->
                campaignProductRepository.save(new CampaignProduct(newCampaign.getId(), product.getId(),
                        campaign.getBid(), product.getCategory()))));

        campaignProductRepository.flush();

        return newCampaign;
    }


    @Override
    public Product serverAd(String category) {
        // Delete from the merged table the deprecated campaigns (and associated products).
        campaignProductRetention(CAMPAIGN_TTL_DAYS);

        // Find a product with the highest bid and the given category
        List<CampaignProduct> campaignProductList = campaignProductRepository.findByCategoryByMaxBid(category);
        if (campaignProductList.size() < 1) {
            // If there is no such category get any product with the highest bid.
            campaignProductList = campaignProductRepository.findByMaxBid();
            if (campaignProductList.size() < 1) {
                return null;
            }
        }

        // Get the full product details from the Products table and return it.
        return productRepository.findById(campaignProductList.get(0).getProductId()).get();

    }

    /**
     * Delete from the Campaign_Product table all the campaign that are out-of-date according to the number of day
     * past their start date.
     * @param days after the start date to be considered deprecated (Defined in the application.properties).
     */
    private void campaignProductRetention(int days) {
        // Get all the deprecated campaigns' title.
        List<Integer> oldCampaignsIds = campaignRepository.findAll().stream().filter(
                campaign -> campaign.getStartDate()
                        .before(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days)))
                ).map(Campaign::getId).toList();

        // Delete the deprecated associations in the Campaign-Product table
        campaignProductRepository.deleteAll(campaignProductRepository.findAll().stream().filter(
                        campaignProduct -> oldCampaignsIds.contains(campaignProduct.getCampaignId()))
                        .collect(Collectors.toList()));
    }
}
