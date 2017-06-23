package kg.hybris.config;

import kg.hybris.flows.GuestCheckoutFlow;
import kg.hybris.services.PDFScreenshotReportingService;
import kg.hybris.services.ScreenshotReportingService;
import kg.hybris.setup.DefaultHybrisBrowser;
import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by kaushik on 6/18/2017.
 */
@Configuration
@ComponentScan(basePackages = { "kg.hybris.*" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:hybristester.properties")
public class AppConfig {

    @Value("${chromedriver.path}")
    private String chromeDriverPath;

    @Value("${hybrisbrowser.acceptAllSSLCerts:false}")
    private boolean acceptAllSSLCerts;

    @Value("${hybrisbrowser.script.timeout:10}")
    private Integer scriptTimeout;


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
        ChromeDriver chromeDriver= new ChromeDriver(capability);
        chromeDriver.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        return  chromeDriver;
    }

    @Bean
    public GuestCheckoutFlow guestCheckoutFlow()
    {
        return new GuestCheckoutFlow();
    }

    @Bean
    public ScreenshotReportingService screenshotReportingService()
    {
        return new ScreenshotReportingService();
    }

    @Bean
    public PDFScreenshotReportingService pDFScreenshotReportingService(){return new PDFScreenshotReportingService();}


}
