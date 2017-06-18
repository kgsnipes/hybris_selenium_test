package kg.hybris.actions;

import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by kaushik on 6/18/2017.
 */
public class ProductSearchHybrisUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private String productSearchTerm;

    public ProductSearchHybrisUserAction(HybrisBrowser browser,String productSearchTerm) {
        this.productSearchTerm = productSearchTerm;
        this.setHybrisBrowser(browser);
    }



    @Override
    public void perform()throws Exception
    {
        super.perform();
        WebDriver browser= getHybrisBrowser().getBrowser();
        browser.findElement(By.id("js-site-search-input")).sendKeys(productSearchTerm);
        browser.findElement(By.name("search_form_SearchBox")).submit();
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
