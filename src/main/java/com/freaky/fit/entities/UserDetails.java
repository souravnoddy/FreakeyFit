package com.freaky.fit.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.freaky.fit.enums.Status;
import com.freaky.fit.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails extends AuditableEntity {

    @Id
    private String id;


    private String name;

    private String phoneNumber;

    private String email;


    private Date lastLoggedIn;

    private Date dob;

    private String latitude;

    private String longitude;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String area;

    private String city;

    private String state;

    private String country;


    @Enumerated(EnumType.STRING)
    private Status status;
}
