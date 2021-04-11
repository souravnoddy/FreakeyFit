package com.freaky.fit.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuditableEntity {
    private Date cratedOn;

    private String createdBy;

    private Date modifiedOn;

    private String modifiedBy;
}
