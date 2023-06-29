package concerttours.jobs;

import concerttours.daos.BandDAO;
import concerttours.daos.TokenHolderDAO;
import concerttours.model.TokenHolderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Random;

public class SetTokenJob extends AbstractJobPerformable<CronJobModel>
{
    private static final int TOKEN_LENGTH = 10;

    private ModelService modelService;
    private TokenHolderDAO tokenHolderDAO;
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    public void setTokenHolderDAO(final TokenHolderDAO tokenHolderDAO)
    {
        this.tokenHolderDAO = tokenHolderDAO;
    }

    @Override
    public PerformResult perform(final CronJobModel cronJob)
    {
        final List<TokenHolderModel> tokenHolderModels = tokenHolderDAO.getTokenHolders();

        for(TokenHolderModel tokenHolderModel: tokenHolderModels) {
            tokenHolderModel.setToken(generateNewToken(TOKEN_LENGTH));
        }

        modelService.saveAll(tokenHolderModels);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private String generateNewToken(int tokenLength) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(tokenLength)
                .collect(StringBuilder:: new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}