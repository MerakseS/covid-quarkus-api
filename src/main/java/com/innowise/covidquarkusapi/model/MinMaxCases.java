package com.innowise.covidquarkusapi.model;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinMaxCases {

    private String country;
    private int minCases;
    private ZonedDateTime minCasesDate;
    private int maxCases;
    private ZonedDateTime maxCasesDate;
}
