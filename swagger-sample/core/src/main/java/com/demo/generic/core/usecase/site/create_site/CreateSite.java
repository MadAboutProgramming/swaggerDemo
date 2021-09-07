package com.demo.generic.core.usecase.site.create_site;

import com.demo.generic.core.entity.Site;
import com.demo.generic.core.exception.SiteEnablerException;

import java.util.Optional;

public interface CreateSite {
    Optional<Site> createSite (Site site) throws SiteEnablerException;
}
