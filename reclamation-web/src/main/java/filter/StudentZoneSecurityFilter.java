package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IdentityBean;

@WebFilter("/etudiant/*")
public class StudentZoneSecurityFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		IdentityBean identity = (IdentityBean) req.getSession().getAttribute("identity");
		Boolean letGo = false;
		if (identity!=null &&
				identity.getIdentifiedUser()!=null &&
					identity.hasRole("Etu")
		) {
			letGo = true;
		}
		if(letGo){
			arg2.doFilter(arg0, arg1);
		}else{
			resp.sendRedirect(req.getContextPath() + "/login.jsf");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
