package com.mabaya.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CampaignProductId implements Serializable {
    String campaignName;
    Integer productId;
}
