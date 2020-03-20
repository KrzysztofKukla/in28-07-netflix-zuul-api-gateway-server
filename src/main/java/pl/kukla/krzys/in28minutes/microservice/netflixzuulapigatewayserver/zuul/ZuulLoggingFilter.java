package pl.kukla.krzys.in28minutes.microservice.netflixzuulapigatewayserver.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Krzysztof Kukla
 */
@Component
@Slf4j
public class ZuulLoggingFilter extends ZuulFilter {

    //here we define the filter should happening before request is executed, or after request
    // or we want only filter error request - request cause the Exception to happen
    @Override
    public String filterType() {
        //in this case we want to intercept all requests before execution
        return "pre";
//        return "post";
//        return "error";
    }

    //if we have multiple filters like Zuul logging filter, Zuul security filter etc. we can set priority of them
    @Override
    public int filterOrder() {
        return 1;
    }

    // here we specify if this filter should be executed or not based on any logic for example any request
    @Override
    public boolean shouldFilter() {
        //for now we set up as true - filter will be executed always ( for each request )
        return true;
    }

    //here we specify what this filter should do
    //in this case this filter will only log current request
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest currentRequest = RequestContext.getCurrentContext().getRequest();
        log.info("request-> {}, request uri-> {} ", currentRequest, currentRequest.getRequestURI());
        return null;
    }

}
