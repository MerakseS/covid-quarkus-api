package com.innowise.covidquarkusapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Country {

    @Getter(onMethod_ = @JsonGetter("name"))
    @Setter(onMethod_ = @JsonSetter("Country"))
    private String name;

    @Getter(onMethod_ = @JsonGetter("slug"))
    @Setter(onMethod_ = @JsonSetter("Slug"))
    private String slug;

    @Getter(onMethod_ = @JsonGetter("iso2"))
    @Setter(onMethod_ = @JsonSetter("ISO2"))
    private String iso2;
}
