package com.demo.generic.entrypoints.site_controller;

import com.demo.generic.core.entity.Site;
import com.demo.generic.core.exception.BaseSiteCreatorException;
import com.demo.generic.core.exception.ErrorCodes;
import com.demo.generic.core.usecase.site.ManageSiteUseCases;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/sites")
@RestController
public class SiteController {
    private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    private final ManageSiteUseCases manageSiteUseCases;

    public SiteController(ManageSiteUseCases manageSiteUseCases) {
        this.manageSiteUseCases = manageSiteUseCases;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> createSite(@NotNull @RequestBody Site site)
            throws BaseSiteCreatorException {
        logger.info("Received a request to create a site : {}", site);
        Optional<Site> optionalSite;
        if (site.getSiteNumber() == null) {
            throw new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_INPUT);
        }

        try {
            optionalSite = manageSiteUseCases.createSite(site);
        } catch (BaseSiteCreatorException e) {
            throw new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_INPUT);
        }

        return optionalSite
                .map(savedSite -> ResponseEntity.status(HttpStatus.CREATED).body(toDTO(site)))
                .orElseThrow(
                        () -> new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_INPUT));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> updateSite(@NotNull @RequestBody Site site)
            throws BaseSiteCreatorException {
        logger.info("Received a request to update a site : {}", site);
        Optional<Site> optionalSite;
        if (site.getSiteNumber() == null) {
            throw new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_INPUT);
        }

        try {
            optionalSite = manageSiteUseCases.updateSite(site);
        } catch (BaseSiteCreatorException e) {
            throw new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_INPUT);
        }

        return optionalSite
                .map(savedSite -> ResponseEntity.status(HttpStatus.ACCEPTED).body(toDTO(savedSite)))
                .orElseThrow(
                        () -> new BaseSiteCreatorException(ErrorCodes.Exception_Codes.SITE_NOT_FOUND));
    }


    @DeleteMapping(path = "/{siteName}")
    public ResponseEntity<String> deleteSite(@NotNull @PathVariable("siteName") String siteName)
            throws BaseSiteCreatorException {
        logger.info("Received a request to delete a site : {}", siteName);

        try {
            manageSiteUseCases.deleteSite(siteName);
        } catch (BaseSiteCreatorException e) {
            throw new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_INPUT);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> getSiteBySiteUUIDOrDeviceId(
            @RequestParam(name = "siteNumber", required = false) String siteNumber,
            @RequestParam(name = "siteName", required = false) String siteName) throws BaseSiteCreatorException {
        if (siteNumber != null) {
            logger.info("Received a request to fetch a site by siteNumber and siteName: {}", siteNumber);
            Optional<Site> site = manageSiteUseCases.getSiteDetails(siteName, siteNumber);
            return site
                    .map(obtainedSite -> ResponseEntity.status(HttpStatus.OK).body(toDTO(obtainedSite)))
                    .orElseThrow(
                            () -> new BaseSiteCreatorException(ErrorCodes.Exception_Codes.SITE_NOT_FOUND));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    private SiteDTO toDTO(Site site) {
        SiteDTO dto = new SiteDTO();

        dto.setName(site.getName());
        dto.setSiteNumber(site.getSiteNumber());
        dto.setContactNumber(site.getContactNumber());

        return dto;
    }
}
