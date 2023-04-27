package com.innowise.covidquarkusapi.service;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.innowise.covidquarkusapi.client.CovidApiClient;
import com.innowise.covidquarkusapi.model.Country;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CovidServiceImpl implements CovidService {

    @Inject
    @RestClient
    CovidApiClient covidApiClient;

    @Override
    public List<Country> getCountryList() {
        List<Country> countryList = covidApiClient.getCountryList();
        log.info("Successfully got country list");
        return countryList;
    }
}
