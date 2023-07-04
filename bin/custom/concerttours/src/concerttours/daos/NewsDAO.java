package concerttours.daos;

import concerttours.model.NewsModel;

import java.util.List;

/**
 * An interface for the Band DAO including various operations for retrieving persisted Band model objects
 */
public interface NewsDAO {
    /**
     * Return a list of band models that are currently persisted. If none are found an empty list is returned.
     *
     * @return all the bands in the system
     */
    List<NewsModel> findNews();
    /**
     * Finds all bands with given code. If none is found, an empty list will be returned.
     *
     * @param code
     *           the code to search for bands
     * @return All bands with the given code.
     */
}