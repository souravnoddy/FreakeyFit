package com.freaky.fit.entities;

import com.freaky.fit.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymDetails extends AuditableEntity {

    @Id
    private String id;

    private String specialities;

    private String area;

    private String city;

    private String state;

    private String country;

    private boolean activationStatus;

    // TODO: 11/4/21 image to stored

    private String owner;

    private String remarks;
    @Enumerated(EnumType.STRING)
    private Status status;


}
