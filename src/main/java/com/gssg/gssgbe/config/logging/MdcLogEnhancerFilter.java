package com.gssg.gssgbe.config.logging;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class MdcLogEnhancerFilter implements Filter {

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
		throws IOException, ServletException {
		MDC.put("transactionId", UUID.randomUUID().toString());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		MDC.clear();
		Filter.super.destroy();
	}
}
