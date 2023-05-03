package com.innowise.covidquarkusapi.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CovidCases {

    @JsonSetter("Country")
    private String country;

    @JsonSetter("Province")
    private String province;

    @JsonSetter("Cases")
    private int cases;

    @JsonSetter("Date")
    private ZonedDateTime date;
}
