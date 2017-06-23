package kg.hybris.actions;

import kg.hybris.dto.FlowActionResult;
import kg.hybris.setup.HybrisBrowser;

/**
 * Created by kaushik on 6/18/2017.
 */
public interface HybrisUserAction {

    public void perform() throws Exception;
    public String getName();
    public void setHybrisBrowser(HybrisBrowser browser);
    public HybrisBrowser getHybrisBrowser();
    public void preActionActivities();
    public void postActionActivities();
    public void actionFailureActivites(Exception ex);
    public void createHybrisActionResult(String actionName);
    public FlowActionResult getActionResult();
}
