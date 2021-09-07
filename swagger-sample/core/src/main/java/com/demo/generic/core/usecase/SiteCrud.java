package com.demo.generic.core.usecase;

import com.demo.generic.core.usecase.site.create_site.CreateSite;
import com.demo.generic.core.usecase.site.delete_site.DeleteSite;
import com.demo.generic.core.usecase.site.update_site.UpdateSite;
import com.demo.generic.core.usecase.site.get_site.GetSiteDetails;

/**
 * All CRUD operations on the Site entity.
 *
 * @author avinash
 */
public interface SiteCrud extends CreateSite, GetSiteDetails, UpdateSite, DeleteSite {

}
