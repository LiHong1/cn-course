package cm.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by li hong on 2015/5/21.
 */
public class CmUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	//用户名
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "number";
	//密码
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	//需要回调的URL 自定义参数
	public static final String SPRING_SECURITY_FORM_REDERICT_KEY = "spring-security-redirect";

	@Deprecated
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";

	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
	private String redirectParameter = SPRING_SECURITY_FORM_REDERICT_KEY;
	private boolean postOnly = true;


	public CmUsernamePasswordAuthenticationFilter() {
		super();

	}

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		System.out.println("=================");
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String redirectUrl = obtainRedercitUrl(request);
		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		//自定义回调URL，若存在则放入Session
		if(redirectUrl != null && !"".equals(redirectUrl)){
			request.getSession().setAttribute("callCustomRediretUrl", redirectUrl);
		}
		username = username.trim();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}


	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}



	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}


	protected String obtainRedercitUrl(HttpServletRequest request) {
		return request.getParameter(redirectParameter);
	}


	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}


	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}


	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}


	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

}
