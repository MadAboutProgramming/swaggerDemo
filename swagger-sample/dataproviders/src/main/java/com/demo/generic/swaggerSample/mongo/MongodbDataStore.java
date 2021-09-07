package com.demo.generic.swaggerSample.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.demo.generic.core.entity.Site;
import com.demo.generic.core.exception.SiteEnablerException;
import com.demo.generic.core.exception.ErrorCodes;
import com.demo.generic.core.usecase.SiteCrud;
import lombok.Data;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import static com.demo.generic.core.entity.Constants.SiteJsonKeys.*;

/**
 * Interface with a running mongodb instance to perform CRUD operations on configurations.
 *
 * @author avinash
 */

@Data
public class MongodbDataStore implements SiteCrud {

    private static final Logger LOG = LoggerFactory.getLogger(MongodbDataStore.class);
    private String hostName;
    private String databaseName;
    private String collectionName;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongodbDataStore(String hostName, String databaseName, String collectionName) {
        this.hostName = hostName;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        mongoClient = MongoClients.create(hostName);
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    @Override
    public Optional<Site> createSite(Site site) throws SiteEnablerException {
        if (ConfigurationDocument.isValidSite(site))
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.INVALID_SITE, site);

        Iterable<Document> document;
        try {
            document =
                    collection.find(and(
                            eq(SITE_NAME, site.getName())));
            if (document.iterator().hasNext())
                throw new SiteEnablerException(ErrorCodes.Exception_Codes.SITE_ALREADY_EXIST, site);
            this.collection.insertOne(ConfigurationDocument.mapSiteToDocument(site));
        } catch (MongoException e) {
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.INVALID_SITE, site);
        }
        LOG.debug("Added a new site to the mongodb: {}", site);
        return getSite(site.getName(), site.getSiteNumber());
    }

    @Override
    public Optional<Site> updateSite(Site site) throws SiteEnablerException {
        if (ConfigurationDocument.isValidSite(site))
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.INVALID_SITE, site);

        try {
            Document oneAndDelete = this.collection.findOneAndDelete(and(
                    eq(SITE_UUID, site.getSiteUUID()),
                    eq(SITE_NUMBER, site.getSiteNumber())));
            LOG.debug("The following site was deleted: {}", oneAndDelete);
            this.collection.insertOne(ConfigurationDocument.mapSiteToDocument(site));
        } catch (MongoException e) {
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.INVALID_SITE, site);
        }
        LOG.debug("Updated site to the mongodb: {}", site);
        return getSite(site.getName(), site.getSiteNumber());
    }

    @Override
    public void deleteSite(String siteName) throws SiteEnablerException {

        LOG.debug("Deleted collection {}", siteName);
        Iterable<Document> documents;
        try {
            documents =
                    collection.find(and(
                            eq(SITE_NAME, siteName)));

            while (documents.iterator().hasNext()) {
                collection.deleteMany(documents.iterator().next());
            }
        } catch (MongoException e) {
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.SITE_NOT_FOUND);
        }

    }

    @Override
    public Optional<Site> getSite(String siteName, String siteNumber) throws SiteEnablerException {
        Document document;
        try {
            document =
                    collection.find(and(
                            eq(SITE_NAME, siteName),
                            eq(SITE_NUMBER, siteNumber)))
                            .first();
        } catch (MongoException e) {
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.SITE_NOT_FOUND);
        }
        if (!ConfigurationDocument.isValidSiteDocument(document)) {
            throw new SiteEnablerException(ErrorCodes.Exception_Codes.SITE_NOT_FOUND);
        }
        LOG.debug("Returning document: {}", document);
        return Optional.of(ConfigurationDocument.mapDocumentToSite(document));
    }
}