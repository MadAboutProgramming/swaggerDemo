package com.demo.generic.configuration;

import com.demo.generic.core.usecase.site.ManageSiteUseCases;
import com.demo.generic.core.usecase.site.create_site.CreateSite;
import com.demo.generic.core.usecase.site.delete_site.DeleteSite;
import com.demo.generic.core.usecase.site.get_site.GetSiteDetails;
import com.demo.generic.core.usecase.site.update_site.UpdateSite;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class UseCaseConfiguration {

    @Bean
    public ManageSiteUseCases manageSiteUseCases(CreateSite createSite, UpdateSite updateSite, GetSiteDetails getSiteDetails,
                                                          DeleteSite deleteSite) {
        return new ManageSiteUseCases(createSite, getSiteDetails, updateSite, deleteSite);
    }

}
