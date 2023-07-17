package com.mabaya.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campaign implements Serializable {
    @Id
    String name;

    Double bid;

    Date startDate;

    Set<Integer> productIdentifierToPromote;

}
