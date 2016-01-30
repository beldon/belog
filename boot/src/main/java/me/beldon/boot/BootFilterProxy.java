package me.beldon.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;

/**
 * 替换 Spring DelegatingFilterProxy
 *
 * @see org.springframework.web.filter.DelegatingFilterProxy
 * Created by Beldon
 */
public class BootFilterProxy extends GenericFilterBean {

    private String contextAttribute;

    private WebApplicationContext webApplicationContext;

    private String targetBeanName;

    private boolean targetFilterLifecycle = false;

    private volatile Filter delegate;

    private final Object delegateMonitor = new Object();


    public BootFilterProxy() {
    }


    public BootFilterProxy(Filter delegate) {
        Assert.notNull(delegate, "delegate Filter object must not be null");
        this.delegate = delegate;
    }

    public BootFilterProxy(String targetBeanName) {
        this(targetBeanName, null);
    }


    public BootFilterProxy(String targetBeanName, WebApplicationContext wac) {
        Assert.hasText(targetBeanName, "target Filter bean name must not be null or empty");
        this.setTargetBeanName(targetBeanName);
        this.webApplicationContext = wac;
        if (wac != null) {
            this.setEnvironment(wac.getEnvironment());
        }
    }


    public void setContextAttribute(String contextAttribute) {
        this.contextAttribute = contextAttribute;
    }


    public String getContextAttribute() {
        return this.contextAttribute;
    }

    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    protected String getTargetBeanName() {
        return this.targetBeanName;
    }


    public void setTargetFilterLifecycle(boolean targetFilterLifecycle) {
        this.targetFilterLifecycle = targetFilterLifecycle;
    }

    /**
     * Return whether to invoke the {@code Filter.init} and
     * {@code Filter.destroy} lifecycle methods on the target bean.
     */
    protected boolean isTargetFilterLifecycle() {
        return this.targetFilterLifecycle;
    }


    @Override
    public void initFilterBean() throws ServletException {
        synchronized (this.delegateMonitor) {
            if (this.delegate == null) {
                // If no target bean name specified, use filter name.
                if (this.targetBeanName == null) {
                    this.targetBeanName = getFilterName();
                }
                // Fetch Spring root application context and initialize the delegate early,
                // if possible. If the root application context will be started after this
                // filter proxy, we'll have to resort to lazy initialization.
                WebApplicationContext wac = findWebApplicationContext();
                if (wac != null) {
                    this.delegate = initDelegate(wac);
                    if (wac.containsBean("bootService")) {
                        BootFilter bootFilter =  wac.getBean("bootService",BootFilter.class);
                        bootFilter.setBootFilter(this);
                    }
                }
            }
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Filter delegateToUse = this.delegate;
        if (delegateToUse == null) {
            filterChain.doFilter(request, response);
        }else{
            // Let the delegate perform the actual doFilter operation.
            invokeDelegate(delegateToUse, request, response, filterChain);
        }
    }

    @Override
    public void destroy() {
        Filter delegateToUse = this.delegate;
        if (delegateToUse != null) {
            destroyDelegate(delegateToUse);
        }
    }


    protected WebApplicationContext findWebApplicationContext() {
        if (this.webApplicationContext != null) {
            // the user has injected a context at construction time -> use it
            if (this.webApplicationContext instanceof ConfigurableApplicationContext) {
                if (!((ConfigurableApplicationContext) this.webApplicationContext).isActive()) {
                    // the context has not yet been refreshed -> do so before returning it
                    ((ConfigurableApplicationContext) this.webApplicationContext).refresh();
                }
            }
            return this.webApplicationContext;
        }
        String attrName = getContextAttribute();
        if (attrName != null) {
            return WebApplicationContextUtils.getWebApplicationContext(getServletContext(), attrName);
        } else {
            return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        }
    }


    public Filter initDelegate(WebApplicationContext wac) throws ServletException {
        Filter delegate = null;
        ApplicationContext temp = wac;

        while (temp != null) {
            if (temp.containsBean(getTargetBeanName())) {
                delegate = temp.getBean(getTargetBeanName(), Filter.class);
                if (isTargetFilterLifecycle()) {
                    delegate.init(getFilterConfig());
                }
                return delegate;
            }
            temp = temp.getParent();
        }
        return delegate;
    }


    protected void invokeDelegate(
            Filter delegate, ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        delegate.doFilter(request, response, filterChain);
    }

    protected void destroyDelegate(Filter delegate) {
        if (isTargetFilterLifecycle()) {
            delegate.destroy();
        }
    }


    interface BootFilter {
        void setBootFilter(BootFilterProxy bootFilterProxy);
    }
}
