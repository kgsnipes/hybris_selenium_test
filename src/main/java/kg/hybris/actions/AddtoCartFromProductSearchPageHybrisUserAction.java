package kg.hybris.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

/**
 * Created by kaushik on 6/18/2017.
 */

public class AddtoCartFromProductSearchPageHybrisUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private Integer productsTOAdd;

    public AddtoCartFromProductSearchPageHybrisUserAction(  Integer productsTOAdd) {
        this.productsTOAdd = productsTOAdd;

    }


    public void perform()throws Exception
    {

        WebDriver browser= getHybrisBrowser().getBrowser();
        List<WebElement> addTOCartForms= browser.findElements(By.className("add_to_cart_form"));
        Iterator<WebElement> iterator=addTOCartForms.iterator();
        while(iterator.hasNext())
        {
            if(productsTOAdd>0)
            {
                WebElement addTOCartButton= iterator.next().findElement(By.tagName("button"));
                if(addTOCartButton!=null && addTOCartButton.isEnabled())
                {
                    addTOCartButton.click();
                    WebDriverWait wait = new WebDriverWait(browser, 5000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorbox")));
                    browser.findElement(By.id("cboxClose")).click();
                }
                --productsTOAdd;
            }
            else
            {
                break;
            }
            Thread.sleep(1000);
        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
