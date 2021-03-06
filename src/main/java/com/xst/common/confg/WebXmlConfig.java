package com.xst.common.confg;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.xst.common.filter.XssFilter;
import com.xst.common.interceptor.LoginRequestInterceptor;
import com.xst.common.interceptor.MaliciousRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.EventListener;

/**
 * web访问配置
 * @author CZH
 */
@Configuration
public class WebXmlConfig extends WebMvcConfigurerAdapter{

	/**
	 * 静态资源访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}


	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginRequestInterceptor()).addPathPatterns("/xst/**").excludePathPatterns("/xst/admin/login").excludePathPatterns("/xst/admin/logout");
		registry.addInterceptor(new MaliciousRequestInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	/**
	 * 过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		// filter只能配置"/*","/**"无法识别
		registration.addUrlPatterns("/xst/admin/*");
		return registration;
	}


    @Autowired
    private Environment env;


	/**
	 * 访问druid监控信息servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 添加初始化参数：initParams

		// 白名单：
		registration.addInitParameter("allow", env.getProperty("druid.allow"));
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		registration.addInitParameter("deny", env.getProperty("druid.deny"));
		// 登录查看信息的账号密码.
		registration.addInitParameter("loginUsername", env.getProperty("druid.loginUsername"));
		registration.addInitParameter("loginPassword", env.getProperty("druid.loginPassword"));
		// 是否能够重置数据.
		registration.addInitParameter("resetEnable", env.getProperty("druid.resetEnable"));
		return registration;
	}

	/**
	 * 过滤druid
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
