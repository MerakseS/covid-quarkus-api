package com.innowise.covidquarkusapi.service;

import java.util.List;

import com.innowise.covidquarkusapi.model.Country;

public interface CovidService {

    List<Country> getCountryList();
}
