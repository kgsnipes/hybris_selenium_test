package kg.hybris.setup;

import kg.hybris.actions.HybrisUserAction;
import kg.hybris.flows.HybrisFlow;
import kg.hybris.services.ScreenshotReportingService;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
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

    @Autowired
    private ScreenshotReportingService screenshotReportingService;

    private boolean isModeSet=false;
    private HybrisFlow flow;
    private BrowserDisplayMode mode;

    public Long getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(Long sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public boolean isModeSet() {
        return isModeSet;
    }

    public void setisModeSet(boolean isModeSet) {
        this.isModeSet = isModeSet;
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
        if(!this.isModeSet)
        {
            if(this.mode!=null)
            {
                Dimension d=null;
                switch (this.mode)
                {
                    case MOBILE:
                        d = new Dimension(640,960);
                        //Resize the current window to the given dimension
                        getBrowser().manage().window().setSize(d);

                        break;
                    case TABLET:
                        d = new Dimension(1024,600);
                        //Resize the current window to the given dimension
                        getBrowser().manage().window().setSize(d);
                        break;
                    default:
                        getBrowser().manage().window().maximize();
                            break;
                }
            }
            else {
                getBrowser().manage().window().maximize();
                this.mode=BrowserDisplayMode.DESKTOP;
            }
            this.isModeSet=!this.isModeSet;
        }
        try {
            userAction.setHybrisBrowser(this);
            userAction.createHybrisActionResult(userAction.getName());

            LOG.info(userAction.getName() + " Action is executing");
            userAction.preActionActivities();
            screenshotReportingService.saveScreenshot(this,userAction);
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

    public ScreenshotReportingService getScreenshotReportingService() {
        return screenshotReportingService;
    }

    public BrowserDisplayMode getDisplayMode() {
        return this.mode;
    }

    public void setBrowserDisplayMode(BrowserDisplayMode mode) {
        this.mode=mode;
        this.isModeSet=false;

    }

    public void setScreenshotReportingService(ScreenshotReportingService screenshotReportingService) {
        this.screenshotReportingService = screenshotReportingService;
    }


}
