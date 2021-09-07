package com.demo.generic.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {

    private String siteNumber;

    private UUID siteUUID;

    private String name;

    private String contactNumber;
}
