package kg.hybris.setup;

import kg.hybris.actions.HybrisUserAction;
import org.openqa.selenium.WebDriver;

/**
 * Created by kaushik on 6/18/2017.
 */
public interface HybrisBrowser {
    public WebDriver getBrowser() throws Exception;
    public HybrisBrowser executeAction(HybrisUserAction userAction) throws Exception;
    public String getHOST();

}
