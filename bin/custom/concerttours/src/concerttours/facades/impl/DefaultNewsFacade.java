package concerttours.facades.impl;

import concerttours.daos.NewsDAO;
import concerttours.data.NewsData;
import concerttours.facades.NewsFacade;
import concerttours.model.NewsModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultNewsFacade implements NewsFacade {
    private NewsDAO newsDAO;

    private static final String CATALOG_ID = "concertToursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";
    @Override
    public List<NewsData> getNews() {
        final List<NewsModel> newsModels = newsDAO.findNews().stream()
                .filter(news ->
                {
                    CatalogVersionModel catalogVersionModel = news.getCatalogVersion();
                    return catalogVersionModel.getVersion().equals(CATALOG_VERSION_NAME) &&
                            catalogVersionModel.getCatalog().getId().equals(CATALOG_ID);
                })
                .toList();
        final List<NewsData> newsFacadeData = new ArrayList<>();

        if (!newsModels.isEmpty()) //6.2
        {
            for (final NewsModel sm : newsModels) {
                final NewsData sfd = new NewsData();
                sfd.setDate(sm.getDate());
                sfd.setContent(sm.getContent());
                sfd.setHeadline(sm.getHeadline());
                newsFacadeData.add(sfd);
            }
        }
        return newsFacadeData;
    }

    @Required
    public void setNewsDAO(final NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}