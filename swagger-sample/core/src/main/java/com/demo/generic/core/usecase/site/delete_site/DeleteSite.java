package com.demo.generic.core.usecase.site.delete_site;

import com.demo.generic.core.exception.SiteEnablerException;


public interface DeleteSite {
    void deleteSite(String siteName) throws SiteEnablerException;
}
