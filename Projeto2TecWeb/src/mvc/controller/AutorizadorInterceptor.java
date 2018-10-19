package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
	String uri = request.getRequestURI();
	
	if( uri.endsWith("/") ||
	uri.endsWith("efetuaLogin") ||
	 uri.endsWith("login") ||
	uri.endsWith("cria") ||  
	uri.endsWith("cadastro")||
	uri.endsWith("loga")
			) {
	 return true;
	}
	if(request.getSession().getAttribute("usuarioLogado") != null) {
	 return true;
	}
	response.sendRedirect("login");
	return false;
	}
	}