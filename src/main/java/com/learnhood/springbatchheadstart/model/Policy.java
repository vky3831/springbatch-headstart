package com.learnhood.springbatchheadstart.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Policy {
    @Id
    Integer policyId;
    String statecode;
    String county;
    Double eqSiteLimit;
    Double huSiteLimit;
    Double flSiteLimit;
    Double frSiteLimit;
    @Column(name = "tiv_2011")
    Double tiv2011;
    @Column(name = "tiv_2012")
    Double tiv2012;
    Double eqSiteDeductible;
    Double huSiteDeductible;
    Double flSiteDeductible;
    Double frSiteDeductible;
    Double pointLatitude;
    Double pointLongitude;
    String line;
    String construction;
    Integer pointGranularity;

}
