package com.mabaya.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CampaignProductId.class)
public class CampaignProduct implements Serializable {
    @Id
    String campaignName;
    @Id
    Integer productId;

    Double bid;
    String category;
}
