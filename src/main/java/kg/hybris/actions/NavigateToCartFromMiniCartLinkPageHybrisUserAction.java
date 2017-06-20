package kg.hybris.actions;

import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kaushik on 6/18/2017.
 */
public class NavigateToCartFromMiniCartLinkPageHybrisUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private String cartPageURL;

    public NavigateToCartFromMiniCartLinkPageHybrisUserAction(HybrisBrowser browser,String cartPageURL) {
        this.cartPageURL=cartPageURL;
        this.setHybrisBrowser(browser);
    }

    public String getCartPageURL() {
        return cartPageURL;
    }

    public void setCartPageURL(String cartPageURL) {
        this.cartPageURL = cartPageURL;
    }

    @Override
    public void perform()throws Exception
    {
        super.perform();
        WebDriver browser= getHybrisBrowser().getBrowser();
        WebElement miniCartLink= browser.findElement(By.className("js-mini-cart-link"));
        if(miniCartLink!=null)
        {
            JavascriptExecutor js = (JavascriptExecutor)browser;
            js.executeScript("$('.js-mini-cart-link').click();");
            WebDriverWait wait = new WebDriverWait(browser, 10000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorbox")));
            Thread.sleep(2000);
            System.out.println(browser.getCurrentUrl());
            browser.get(getHybrisBrowser().getHOST()+getHybrisBrowser().getContextPath()+getCartPageURL());
            WebDriverWait waitForCartHeader = new WebDriverWait(browser, 10000);
            waitForCartHeader.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-header")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
