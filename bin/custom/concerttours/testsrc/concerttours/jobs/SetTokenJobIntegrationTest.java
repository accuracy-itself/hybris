package concerttours.jobs;

import concerttours.model.NewsModel;
import concerttours.model.TokenHolderModel;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@IntegrationTest
public class SetTokenJobIntegrationTest extends ServicelayerTransactionalTest {
    @Resource
    private ModelService modelService;
    @Resource
    private SetTokenJob setTokenJob;

    @Before
    public void setUp() throws Exception {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException exc) {
        }
    }

    @Test
    public void testSettingTokens() throws Exception {
        String token1 = "1234567890";
        final TokenHolderModel tokenHolderModel1 = modelService.create(TokenHolderModel.class);
        tokenHolderModel1.setToken(token1);

        modelService.saveAll();
        final PerformResult result = setTokenJob.perform(null);
        Assert.assertEquals("Job did not perform correctly", CronJobResult.SUCCESS, result.getResult());

        final TokenHolderModel tokenHolderModel1modified = modelService.get(tokenHolderModel1.getPk());
        Assert.assertEquals(tokenHolderModel1.getToken(), tokenHolderModel1modified.getToken());
        Assert.assertEquals(tokenHolderModel1, tokenHolderModel1modified);
        Assert.assertNotEquals(token1, tokenHolderModel1modified.getToken());

    }

    @After
    public void tearDown() {

    }
}