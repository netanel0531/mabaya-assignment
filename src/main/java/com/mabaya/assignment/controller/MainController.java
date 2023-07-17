package com.mabaya.assignment.controller;

import com.mabaya.assignment.model.Campaign;
import com.mabaya.assignment.model.Product;
import com.mabaya.assignment.service.SponsoredAdsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    SponsoredAdsService sponsoredAdsService;
    public MainController(SponsoredAdsService service) {
        this.sponsoredAdsService = service;
    }

    @PostMapping("/create_campaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        if (null != campaign) {
            return new ResponseEntity<>(sponsoredAdsService.createCampaign(campaign), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/serve_add")
    public ResponseEntity<Product> serveAdd(@RequestParam String category) {
        if (category != null) {
            Product product = sponsoredAdsService.serverAd(category);
            if (product != null)
                return ResponseEntity.ok(product);
            else
                return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("API is healthy");
    }
}
