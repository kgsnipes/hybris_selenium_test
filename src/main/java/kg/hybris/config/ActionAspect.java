package kg.hybris.config;

import kg.hybris.actions.HybrisUserAction;
import kg.hybris.dto.FlowStatus;
import kg.hybris.flows.HybrisFlow;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by kaushik on 6/22/2017.
 */

@Aspect
public class ActionAspect {

    private static final Logger LOG = Logger.getLogger(FlowAspect.class);

    @Around("execution(* kg.hybris.actions.*.perform(..))")
    public void aroundPerformFlow(ProceedingJoinPoint pjp) throws Throwable {


        if (pjp.getThis() instanceof HybrisUserAction) {
            HybrisUserAction action = (HybrisUserAction) pjp.getThis();
            action.createHybrisActionResult(action.getName());
            try {
                LOG.info(action.getName() + " Action is executing");
                action.preActionActivities();
                pjp.proceed();
                action.postActionActivities();
                LOG.info(action.getName() + " Action execution ended");

            } catch (Exception ex) {
                LOG.error(action.getName() + " Encountered an Exception");
                action.actionFailureActivites(ex);
            } finally {

                action.getHybrisBrowser().getFlow().getFlowResult().getActionResultList().add(action.getActionResult());
            }

        }
    }
}
