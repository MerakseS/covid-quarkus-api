package com.innowise.covidquarkusapi.service;

import java.util.List;

import com.innowise.covidquarkusapi.model.Country;
import com.innowise.covidquarkusapi.model.MinMaxCases;

public interface CovidService {

    List<Country> getCountryList();

    MinMaxCases getMinMaxCases(String country, String from, String to);
}
