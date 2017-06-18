package kg.hybris.config;

import kg.hybris.flows.GuestCheckoutFlow;
import kg.hybris.setup.DefaultHybrisBrowser;
import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

/**
 * Created by kaushik on 6/18/2017.
 */
@Configuration
@ComponentScan(basePackages = { "kg.hybris.*" })
@PropertySource("classpath:hybristester.properties")
public class AppConfig {

    @Value("${chromedriver.path}")
    private String chromeDriverPath;

    @Value("${hybrisbrowser.acceptAllSSLCerts:false}")
    private boolean acceptAllSSLCerts;


    @Bean
    public HybrisBrowser hybrisBrowser()
    {
        return new DefaultHybrisBrowser();
    }

    @Bean
    public WebDriver chromeDriver()
    {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        // To Accept SSL certificate
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, acceptAllSSLCerts);
        // setting system property for Chrome browser
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        // create Google Chrome instance and maximize it
        ChromeDriver chromeDriver= new ChromeDriver(capability);
        chromeDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        return  chromeDriver;
    }

    @Bean
    public GuestCheckoutFlow guestCheckoutFlow()
    {
        return new GuestCheckoutFlow();
    }


}
