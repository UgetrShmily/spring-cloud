package cn.my.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.my.web.util.JsonResult;

@Component
public class AccessFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		 
		RequestContext currentContext = RequestContext.getCurrentContext();
		String serviceId=(String) currentContext.get(FilterConstants.SERVICE_ID_KEY);
		if(serviceId.equals("item-service"))
			return true;
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		String token=request.getParameter("token");
		if(token==null) {
			//阻止请求后台微服务
			currentContext.setSendZuulResponse(false);
			//向客户端响应
			currentContext.setResponseStatusCode(200);
			currentContext.setResponseBody(JsonResult.err().code(JsonResult.NOT_LOGIN).toString());
		}
		return null;
	}

	@Override
	public String filterType() {
		
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		//>5
		return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
	}

}
