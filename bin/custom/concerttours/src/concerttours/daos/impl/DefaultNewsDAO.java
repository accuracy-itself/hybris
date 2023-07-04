package concerttours.daos.impl;

import concerttours.daos.NewsDAO;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "newsDAO")
public class DefaultNewsDAO implements NewsDAO {
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<NewsModel> findNews() {
        final String queryString = //
                "SELECT {p:" + NewsModel.PK + "} "//
                        + "FROM {" + NewsModel._TYPECODE + " AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

        return flexibleSearchService.<NewsModel>search(query).getResult();
    }
}