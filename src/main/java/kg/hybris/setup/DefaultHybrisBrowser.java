package kg.hybris.setup;

import kg.hybris.actions.HybrisUserAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

/**
 * Created by kaushik on 6/18/2017.
 */
public class DefaultHybrisBrowser implements HybrisBrowser {


    @Value("${hybrisbrowser.storefront.host}")
    private String HOST;

    @Value("${hybrisbrowser.storefront.contextpath}")
    private String contextPath;

    @Value("${hybrisbrowser.action.sleepinterval}")
    private Long sleepInterval;

    @Autowired
    private WebDriver chromeDriver;

    private boolean isMaximized=false;

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
            userAction.perform();
        }
        catch (Exception ex)
        {
            System.out.println("------------------ Failed at "+userAction.getName()+" ------------------");
            throw ex;
        }
        Thread.sleep(getSleepInterval());

        return this;
    }



}
