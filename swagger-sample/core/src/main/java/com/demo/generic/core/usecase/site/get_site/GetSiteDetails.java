package com.demo.generic.core.usecase.site.get_site;

import com.demo.generic.core.entity.Site;
import com.demo.generic.core.exception.SiteEnablerException;

import java.util.Optional;

public interface GetSiteDetails {
    Optional<Site> getSite(String siteName, String siteNumber) throws SiteEnablerException;
}
