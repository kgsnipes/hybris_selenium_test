package kg.hybris.config;

import kg.hybris.flows.HybrisFlow;
import kg.hybris.setup.HybrisBrowser;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by kaushik on 6/22/2017.
 */

@Aspect
@Component
public class FlowAspect {

    private static final Logger LOG = Logger.getLogger(FlowAspect.class);

    @Around("execution(* kg.hybris.flows.*.performFlow(..))")
    public void aroundPerformFlow(ProceedingJoinPoint pjp) throws Throwable {


       if(pjp.getThis() instanceof HybrisFlow)
       {
           HybrisFlow flow= (HybrisFlow) pjp.getThis();
           flow.createHybrisFlowResult(flow.getName());
           HybrisBrowser browser=null;
           if(pjp.getArgs()[0] instanceof HybrisBrowser)
           {
                browser= (HybrisBrowser) pjp.getArgs()[0];
           }

           try
           {
               LOG.info(flow.getName()+" Flow is executing");
               flow.preFlowActivities();
               pjp.proceed();
               flow.postFlowActivities();
               LOG.info(flow.getName()+" Flow execution ended");

           }
           catch(Exception ex)
           {
               LOG.error(flow.getName()+" Encountered an Exception");
               flow.flowFailureActivites(ex);


           }
           finally {


           }

       }




    }
}
