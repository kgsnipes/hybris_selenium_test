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

/**
 * Created by kaushik on 6/18/2017.
 */
public class DefaultHybrisBrowser implements HybrisBrowser {


    @Value("${hybrisbrowser.storefront.host}")
    private String HOST;

    @Autowired
    private WebDriver chromeDriver;


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
        userAction.perform();
        return this;
    }



}
