package com.innowise.covidquarkusapi.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.innowise.covidquarkusapi.model.Country;
import com.innowise.covidquarkusapi.model.CovidCases;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Singleton
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
public interface CovidApiClient {

    @GET
    @Path("/countries")
    List<Country> getCountryList();

    @GET
    @Path("/country/{country}/status/confirmed")
    List<CovidCases> getCovidCases(@PathParam("country") String country,
        @QueryParam("from") String from, @QueryParam("to") String to);
}
