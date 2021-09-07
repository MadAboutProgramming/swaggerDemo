package com.demo.generic.core.usecase.site;

import com.demo.generic.core.usecase.site.create_site.CreateSite;
import com.demo.generic.core.entity.Site;
import com.demo.generic.core.exception.BaseSiteCreatorException;
import com.demo.generic.core.exception.ErrorCodes;
import com.demo.generic.core.usecase.site.delete_site.DeleteSite;
import com.demo.generic.core.usecase.site.get_site.GetSiteDetails;
import com.demo.generic.core.usecase.site.update_site.UpdateSite;

import java.util.Optional;
import java.util.function.Predicate;

public class ManageSiteUseCases {

    private Predicate<String> notNullPredicate = str -> str != null && !str.trim().isEmpty();

    private CreateSite createSite;

    private UpdateSite updateSite;

    private DeleteSite deleteSite;

    private GetSiteDetails getSiteDetails;

    public ManageSiteUseCases(CreateSite createSite, GetSiteDetails getSiteDetails, UpdateSite updateSite, DeleteSite deleteSite) {
        this.createSite = createSite;
        this.updateSite = updateSite;
        this.deleteSite = deleteSite;
        this.getSiteDetails = getSiteDetails;
    }

    public Optional<Site> createSite(Site site) throws BaseSiteCreatorException {

        validateConfiguration(site);
        return createSite.createSite(site);
    }

    public Optional<Site> updateSite(Site site) throws BaseSiteCreatorException {

        validateConfiguration(site);
        return updateSite.updateSite(site);
    }

    public void deleteSite(String siteName) throws BaseSiteCreatorException {
        deleteSite.deleteSite(siteName);
    }

    public Optional<Site> getSiteDetails(String siteName, String siteNumber) throws BaseSiteCreatorException {
       return getSiteDetails.getSite(siteName, siteNumber);
    }

    private void validateConfiguration(Site site) throws BaseSiteCreatorException {
        Optional<Site> optionalSite = Optional.of(site);
        optionalSite.map(Site::getSiteNumber).filter(name -> notNullPredicate.test(name))
                .orElseThrow(() -> new BaseSiteCreatorException(ErrorCodes.Exception_Codes.INVALID_SITE_NUMBER));
    }
}
