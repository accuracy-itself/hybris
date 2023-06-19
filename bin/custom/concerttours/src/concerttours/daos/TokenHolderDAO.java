package concerttours.daos;

import concerttours.model.TokenHolderModel;

import java.util.List;

public interface TokenHolderDAO {
    List<TokenHolderModel> getTokenHolders();
}