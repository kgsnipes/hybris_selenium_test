package kg.hybris.setup;

import kg.hybris.actions.HybrisUserAction;
import kg.hybris.flows.HybrisFlow;
import kg.hybris.services.ScreenshotReportingService;
import org.openqa.selenium.WebDriver;

import java.awt.*;

/**
 * Created by kaushik on 6/18/2017.
 */
public interface HybrisBrowser {
    public WebDriver getBrowser() throws Exception;
    public HybrisBrowser executeAction(HybrisUserAction userAction) throws Exception;
    public String getHOST();
    public String getContextPath();
    public HybrisFlow getFlow();
    public void setFlow(HybrisFlow flow);
    public ScreenshotReportingService getScreenshotReportingService();
    public BrowserDisplayMode getDisplayMode();
    public void setBrowserDisplayMode(BrowserDisplayMode mode);

}
