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
public class ExerciseProfile {
    @Id
    private String id;

    private String userId;

    private String weight;

    private String height;

    private String exerciseDetails;

    private String rightArm;

    private String leftArm;

    private String chest;

    private String waist;

    private String hips;

    private String thigh;

    private String calf;

    // TODO: 11/4/21 add extra entities

}
