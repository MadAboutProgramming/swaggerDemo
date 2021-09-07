package com.demo.generic.swaggerSample.mongo;

import java.util.*;

import com.demo.generic.core.entity.Site;
import org.bson.Document;

import static com.demo.generic.core.entity.Constants.SiteJsonKeys.*;

public class ConfigurationDocument {
    protected static final Set<String> documentKeySet = new HashSet<>(Arrays.asList(SITE_UUID,
            SITE_NUMBER));

    private ConfigurationDocument() {
    }

    /**
     * Convert a ConfigTemplate to a document.
     *
     * @param site Site
     */
    public static Document mapSiteToDocument(Site site) {
        Document document = new Document();
        document.append(SITE_NAME, site.getName());
        document.append(SITE_UUID, site.getSiteUUID().toString());
        document.append(CONTACT_NUMBER, site.getContactNumber());
        document.append(SITE_NUMBER, site.getSiteNumber());
        return document;
    }

    /**
     * Convert a Document to a Site object.
     *
     * @param document Document
     * @return Site
     */

    public static Site mapDocumentToSite(Document document) {
        Site site = new Site();

        site.setSiteUUID(UUID.fromString(document.getString(SITE_UUID)));
        site.setName(document.getString(SITE_NAME));
        site.setSiteNumber(document.getString(SITE_NUMBER));
        site.setContactNumber(document.getString(CONTACT_NUMBER));

        return site;
    }

    /**
     * Check if a document is valid or not. Should be non-null and contain all the keys required to
     * construct a Site.
     *
     * @param document Document
     * @return true if valid, false otherwise
     */
    public static boolean isValidSiteDocument(Document document) {
        return document != null && (document.keySet().containsAll(documentKeySet));
    }

    /**
     * Check if a configTemplate is valid or not. Should be non-null and contain all the keys.
     *
     * @param site Site
     * @return true if valid, false otherwise
     */
    public static boolean isValidSite(Site site) {
        return site.getSiteUUID() == null || site.getSiteNumber() == null;
    }
}
