package com.innowise.covidquarkusapi.resource;

import java.util.List;

import com.innowise.covidquarkusapi.model.Country;
import com.innowise.covidquarkusapi.service.CovidServiceImpl;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/covid")
public class CovidResource {

    @Inject
    CovidServiceImpl covidService;

    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryList() {
        List<Country> countryList = covidService.getCountryList();
        return Response.ok(countryList).build();
    }
}