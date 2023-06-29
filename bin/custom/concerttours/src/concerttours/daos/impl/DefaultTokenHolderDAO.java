package concerttours.daos.impl;

import concerttours.daos.TokenHolderDAO;
import concerttours.model.TokenHolderModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component(value = "tokenHolderDAO")
public class DefaultTokenHolderDAO implements TokenHolderDAO {
    /**
     * Use SAP
     * Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    /**
     * Finds all TokenHolders by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<TokenHolderModel> getTokenHolders() {
        // Build a query for the flexible search.
        final String queryString = //
                "SELECT {p:" + TokenHolderModel.PK + "} "//
                        + "FROM {" + TokenHolderModel._TYPECODE + " AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

        return flexibleSearchService.<TokenHolderModel>search(query).getResult();
    }
}