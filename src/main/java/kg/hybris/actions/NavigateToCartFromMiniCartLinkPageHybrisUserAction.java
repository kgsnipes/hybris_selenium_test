package kg.hybris.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */

public class NavigateToCartFromMiniCartLinkPageHybrisUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private String cartPageURL;

    public NavigateToCartFromMiniCartLinkPageHybrisUserAction(String cartPageURL) {
        this.cartPageURL=cartPageURL;

    }

    public String getCartPageURL() {
        return cartPageURL;
    }

    public void setCartPageURL(String cartPageURL) {
        this.cartPageURL = cartPageURL;
    }


    public void perform()throws Exception
    {

        WebDriver browser= getHybrisBrowser().getBrowser();
        WebElement miniCartLink= browser.findElement(By.className("js-mini-cart-link"));
        if(miniCartLink!=null)
        {
            JavascriptExecutor js = (JavascriptExecutor)browser;
            js.executeScript("$('.js-mini-cart-link').click();");
            WebDriverWait wait = new WebDriverWait(browser, 10000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorbox")));
            Thread.sleep(2000);
            browser.get(getHybrisBrowser().getHOST()+getHybrisBrowser().getContextPath()+getCartPageURL());
            WebDriverWait waitForCartHeader = new WebDriverWait(browser, 10000);
            waitForCartHeader.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-header")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
