package com.innowise.covidquarkusapi.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.innowise.covidquarkusapi.client.CovidApiClient;
import com.innowise.covidquarkusapi.model.Country;
import com.innowise.covidquarkusapi.model.CovidCases;
import com.innowise.covidquarkusapi.model.MinMaxCases;

import jakarta.ws.rs.NotFoundException;

class CovidServiceTest {

    private CovidService covidService;
    private CovidApiClient covidApiClient;

    @BeforeEach
    void setUp() {
        covidApiClient = Mockito.mock(CovidApiClient.class);

        CovidServiceImpl covidService = new CovidServiceImpl();
        covidService.covidApiClient = covidApiClient;
        this.covidService = covidService;
    }

    @Test
    void getCountryList() {
        List<Country> expected = List.of();
        Mockito.when(covidApiClient.getCountryList())
            .thenReturn(expected);

        List<Country> actual = covidService.getCountryList();

        Mockito.verify(covidApiClient).getCountryList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMinMaxCases() {
        String country = "belarus";
        String from = "2020-04-01T00:00:00Z";
        String to = "2020-04-03T00:00:00Z";
        MinMaxCases expected = new MinMaxCases("Belarus",
            47, ZonedDateTime.parse("2020-04-03T00:00:00Z"),
            141, ZonedDateTime.parse("2020-04-02T00:00:00Z"));

        Mockito.when(covidApiClient.getCovidCases(country, from, to)).thenReturn(List.of(
            new CovidCases("Belarus", "", 163, ZonedDateTime.parse("2020-04-01T00:00:00Z")),
            new CovidCases("Belarus", "", 304, ZonedDateTime.parse("2020-04-02T00:00:00Z")),
            new CovidCases("Belarus", "", 351, ZonedDateTime.parse("2020-04-03T00:00:00Z"))
        ));

        MinMaxCases actual = covidService.getMinMaxCases(country, from, to);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMinMaxCasesForCountryWithProvinces() {
        String country = "united-kingdom";
        String from = "2020-04-01T00:00:00Z";
        String to = "2020-04-03T00:00:00Z";

        MinMaxCases expected = new MinMaxCases("United Kingdom",
            4865, ZonedDateTime.parse("2020-04-02T00:00:00Z"),
            4915, ZonedDateTime.parse("2020-04-03T00:00:00Z"));

        Mockito.when(covidApiClient.getCovidCases(country, from, to)).thenReturn(List.of(
            new CovidCases("United Kingdom", "", 43398, ZonedDateTime.parse("2020-04-01T00:00:00Z")),
            new CovidCases("United Kingdom", "Bermuda", 0, ZonedDateTime.parse("2020-04-01T00:00:00Z")),
            new CovidCases("United Kingdom", "", 48263, ZonedDateTime.parse("2020-04-02T00:00:00Z")),
            new CovidCases("United Kingdom", "Bermuda", 0, ZonedDateTime.parse("2020-04-02T00:00:00Z")),
            new CovidCases("United Kingdom", "", 53178, ZonedDateTime.parse("2020-04-03T00:00:00Z")),
            new CovidCases("United Kingdom", "Bermuda", 0, ZonedDateTime.parse("2020-04-03T00:00:00Z"))
        ));

        MinMaxCases actual = covidService.getMinMaxCases(country, from, to);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void unknownCountry() {
        String country = "unknown-country";
        String from = "2020-04-01T00:00:00Z";
        String to = "2020-04-03T00:00:00Z";

        Mockito.when(covidApiClient.getCovidCases(country, from, to))
            .thenReturn(List.of());

        Assertions.assertThrows(NotFoundException.class,
            () -> covidService.getMinMaxCases(country, from, to));
    }
}