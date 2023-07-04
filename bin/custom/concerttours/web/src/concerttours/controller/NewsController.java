package concerttours.controller;

import concerttours.data.NewsData;
import concerttours.facades.NewsFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NewsController {
    private CatalogVersionService catalogVersionService;
    private NewsFacade newsFacade;

    @RequestMapping(value = "/news")
    public String showNews(final Model model) {
        final List<NewsData> news = newsFacade.getNews();
        model.addAttribute("news", news);
        return "NewsList";
    }

    @Autowired
    public void setCatalogVersionService(final CatalogVersionService catalogVersionServiceService) {
        this.catalogVersionService = catalogVersionServiceService;
    }

    @Autowired
    public void setFacade(final NewsFacade facade) {
        this.newsFacade = facade;
    }
}