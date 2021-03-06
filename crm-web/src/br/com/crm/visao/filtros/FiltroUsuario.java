package br.com.crm.visao.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 */
public class FiltroUsuario implements Filter{

	/**
	 * 
	 */
	@Override
	public void destroy() {
		
	}

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		String usuario = (String) session.getAttribute("usuario");
		
		//Caso tenha o usuario na sessão então chama o doFilter da
		//cadeia de filtros
		//passando o request e response originais para que a requisição
		//interceptada continue o seu fluxo normal.
		if(usuario != null && usuario.trim().length()>0){
			chain.doFilter(request,response);
			return;
		}
		
		String url = req.getRequestURI();
		
		if(!url.equals("/crm-web/pages/TelaLogin.jsp") && 
				!url.equals("/crm-web/ServletLogin")){
			res.sendRedirect("http://localhost:8080/crm-web/pages/TelaLogin.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * 
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
