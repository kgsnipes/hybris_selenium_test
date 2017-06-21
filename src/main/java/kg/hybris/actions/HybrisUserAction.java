package kg.hybris.actions;

import kg.hybris.setup.HybrisBrowser;

/**
 * Created by kaushik on 6/18/2017.
 */
public interface HybrisUserAction {

    public void perform() throws Exception;
    public String getName();
    public void setHybrisBrowser(HybrisBrowser browser);
    public HybrisBrowser getHybrisBrowser();
}
