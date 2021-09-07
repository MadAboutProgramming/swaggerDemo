package com.demo.generic.core.usecase.site.update_site;

import com.demo.generic.core.entity.Site;
import com.demo.generic.core.exception.SiteEnablerException;

import java.util.Optional;

public interface UpdateSite {
    Optional<Site> updateSite(Site site) throws SiteEnablerException;
}
