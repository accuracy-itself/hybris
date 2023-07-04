package concerttours.facades;

import concerttours.data.NewsData;

import java.util.List;

public interface NewsFacade {
    List<NewsData> getNews();
}