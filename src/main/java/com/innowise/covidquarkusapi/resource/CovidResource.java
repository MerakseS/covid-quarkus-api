package com.innowise.covidquarkusapi.resource;

import java.util.List;

import com.innowise.covidquarkusapi.model.Country;
import com.innowise.covidquarkusapi.model.MinMaxCases;
import com.innowise.covidquarkusapi.service.CovidService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/covid")
@Produces(MediaType.APPLICATION_JSON)
public class CovidResource {

    @Inject
    CovidService covidService;

    @GET
    @Path("/countries")
    public Response getCountryList() {
        List<Country> countryList = covidService.getCountryList();
        return Response.ok(countryList).build();
    }

    @GET
    @Path("/country/{country}")
    public Response getMinMaxCases(@PathParam("country") String country,
        @QueryParam("from") String from, @QueryParam("to") String to) {

        MinMaxCases minMaxCases = covidService.getMinMaxCases(country, from, to);
        return Response.ok(minMaxCases).build();
    }
}