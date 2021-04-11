package com.freaky.fit.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymProfile extends AuditableEntity {

    @Id
    private String id;

    private String userId;

    private String gymId;


}
