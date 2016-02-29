package com.washingtonpost.helloadmiral;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.handle((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setHeader(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        response.setHeader(CrossOriginFilter.ACCESS_CONTROL_ALLOW_METHODS_HEADER, "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "true");
        response.setHeader(CrossOriginFilter.ACCESS_CONTROL_MAX_AGE_HEADER, "1728000");
        response.setHeader(CrossOriginFilter.ACCESS_CONTROL_ALLOW_HEADERS_HEADER, "Authorization,Content-Type,Accept,Origin," +
                "User-Agent,DNT,Cache-Control,X-Mx-ReqToken,Keep-Alive,X-Requested-With,If-Modified-Since,X-Auth-Token,X-User-Context");
        response.setHeader("Cache-Control", "no-cache");
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(204);
            response.setContentLength(0);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
