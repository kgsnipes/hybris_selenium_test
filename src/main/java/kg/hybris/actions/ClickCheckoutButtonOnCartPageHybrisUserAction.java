package kg.hybris.actions;

import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */
public class ClickCheckoutButtonOnCartPageHybrisUserAction extends AbstractHybrisUserAction implements HybrisUserAction {



    public ClickCheckoutButtonOnCartPageHybrisUserAction() {


    }




    public void perform()throws Exception
    {

        WebDriver browser= getHybrisBrowser().getBrowser();
        WebElement checkoutLink= browser.findElement(By.className("js-continue-checkout-button"));
        if(checkoutLink!=null)
        {
            checkoutLink.click();
            WebDriverWait wait = new WebDriverWait(browser, 5000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout-login-right-content-component")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
