package com.innowise.covidquarkusapi.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.innowise.covidquarkusapi.client.CovidApiClient;
import com.innowise.covidquarkusapi.model.Country;
import com.innowise.covidquarkusapi.model.CovidCases;
import com.innowise.covidquarkusapi.model.MinMaxCases;

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

    @Override
    public MinMaxCases getMinMaxCases(String country, String from, String to) {
        List<CovidCases> covidCasesList = covidApiClient.getCovidCases(country, from, to);
        List<CovidCases> filteredCovidCasesList = covidCasesList.stream()
            .filter(covidCases -> covidCases.getProvince().isEmpty())
            .toList();

        MinMaxCases minMaxCases = calculateMinMaxCases(filteredCovidCasesList);
        log.info("Successfully calculate Minimal and maximum covid cases. {}", minMaxCases);
        return minMaxCases;
    }

    private MinMaxCases calculateMinMaxCases(List<CovidCases> covidCasesList) {
        int minCases = Integer.MAX_VALUE;
        ZonedDateTime minCasesDate = null;

        int maxCases = Integer.MIN_VALUE;
        ZonedDateTime maxCasesDate = null;

        for (int i = 1; i < covidCasesList.size(); i++) {
            int newCases = covidCasesList.get(i).getCases() - covidCasesList.get(i - 1).getCases();
            if (newCases < minCases) {
                minCases = newCases;
                minCasesDate = covidCasesList.get(i).getDate();
            }

            if (newCases > maxCases) {
                maxCases = newCases;
                maxCasesDate = covidCasesList.get(i).getDate();
            }
        }

        return new MinMaxCases(covidCasesList.get(0).getCountry(),
            minCases, minCasesDate, maxCases, maxCasesDate);
    }
}
