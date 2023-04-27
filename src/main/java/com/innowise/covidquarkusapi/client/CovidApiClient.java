package com.innowise.covidquarkusapi.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.innowise.covidquarkusapi.model.Country;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Singleton
@RegisterRestClient(baseUri = "https://api.covid19api.com")
public interface CovidApiClient {

    @GET
    @Path("/countries")
    List<Country> getCountryList();
}
