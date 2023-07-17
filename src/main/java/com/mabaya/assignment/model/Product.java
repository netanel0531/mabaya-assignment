package com.mabaya.assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product implements Serializable {
    String title;
    String category;
    double price;
    @Id
    Integer id;


}
