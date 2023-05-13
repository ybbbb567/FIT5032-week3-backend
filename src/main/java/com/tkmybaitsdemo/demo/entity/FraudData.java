package com.tkmybaitsdemo.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author yb
 * @date 2023/05/132143
 **/
@Data
public class FraudData {

    @Id
    private String ageGroup;

    private String experiencedOnlineImpersonation;

    private String exposedToScam;

    private String experiencedCardFraud;

    private String experiencedIdentityTheft;
}
