package kg.hybris.actions;

import kg.hybris.dto.ActionStatus;
import kg.hybris.dto.FlowActionResult;
import kg.hybris.setup.HybrisBrowser;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by kaushik on 6/18/2017.
 */

public abstract class AbstractHybrisUserAction implements HybrisUserAction {

    private static final Logger LOG = Logger.getLogger(AbstractHybrisUserAction.class);
    private HybrisBrowser hybrisBrowser;
    private FlowActionResult actionResult;
    public HybrisBrowser getHybrisBrowser() {
        return hybrisBrowser;
    }
    public void setHybrisBrowser(HybrisBrowser hybrisBrowser) {
        this.hybrisBrowser = hybrisBrowser;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
    public void preActionActivities(){
        getActionResult().setStatus(ActionStatus.RUNNING);
        getActionResult().setStartTime(new Date());
    }
    public void postActionActivities(){
        getActionResult().setStatus(ActionStatus.COMPLETED);
        getActionResult().setEndTime(new Date());
    }
    public void actionFailureActivites(Exception ex){
        getActionResult().setStatus(ActionStatus.FAILED);
        getActionResult().setEndTime(new Date());
        getActionResult().setError(ex);
        getActionResult().setErrorMessage(ex.getMessage());
    }
    public void createHybrisActionResult(String actionName){
        this.actionResult=new FlowActionResult();
        actionResult.setActionName(actionName);
        actionResult.setStatus(ActionStatus.STARTED);
    }
    public FlowActionResult getActionResult(){return this.actionResult;}
}
