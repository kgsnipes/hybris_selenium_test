package kg.hybris.setup;

import kg.hybris.actions.HybrisUserAction;
import kg.hybris.flows.HybrisFlow;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by kaushik on 6/18/2017.
 */
public class DefaultHybrisBrowser implements HybrisBrowser {

    private static final Logger LOG = Logger.getLogger(DefaultHybrisBrowser.class);

    @Value("${hybrisbrowser.storefront.host}")
    private String HOST;

    @Value("${hybrisbrowser.storefront.contextpath}")
    private String contextPath;

    @Value("${hybrisbrowser.action.sleepinterval}")
    private Long sleepInterval;

    @Autowired
    private WebDriver chromeDriver;

    private boolean isMaximized=false;
    private HybrisFlow flow;

    public Long getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(Long sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public boolean isMaximized() {
        return isMaximized;
    }

    public void setMaximized(boolean maximized) {
        isMaximized = maximized;
    }

    public String getContextPath() {
        return contextPath;
    }

    public HybrisFlow getFlow() {
        return this.flow;
    }

    public void setFlow(HybrisFlow flow) {
        this.flow=flow;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }

    public WebDriver getBrowser() throws Exception {
    return chromeDriver;
    }

    public HybrisBrowser executeAction(HybrisUserAction userAction) throws Exception {
        if(!this.isMaximized)
        {
            getBrowser().manage().window().maximize();
            this.isMaximized=!this.isMaximized;
        }
        try {
            userAction.setHybrisBrowser(this);
            userAction.createHybrisActionResult(userAction.getName());

            LOG.info(userAction.getName() + " Action is executing");
            userAction.preActionActivities();
            userAction.perform();
            userAction.postActionActivities();
            LOG.info(userAction.getName() + " Action execution ended");

        }
        catch (Exception ex)
        {
            LOG.error(userAction.getName() + " Encountered an Exception");
            userAction.actionFailureActivites(ex);
           throw ex;
        }
        finally {
           getFlow().getFlowResult().getActionResultList().add(userAction.getActionResult());
        }
        Thread.sleep(getSleepInterval());

        return this;
    }



}
