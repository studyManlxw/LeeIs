package com.dhl.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@EnableKnife4j
public class MvcConfig implements WebMvcConfigurer {

    @Override
    /**
     * 重新跨域支持方法
     * CorsRegistry  开启跨域注册
     */
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping 添加可跨域的请求地址
        registry.addMapping("/**")
                //设置跨域 域名权限 规定由某一个指定的域名+端口能访问跨域项目
                .allowedOrigins("*")
                //是否开启cookie跨域
                .allowCredentials(false)
                //规定能够跨域访问的方法类型
                .allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
                //添加验证头信息  token
                .allowedHeaders("*")
                //预检请求存活时间 在此期间不再次发送预检请求
                .maxAge(3600);
    }
    /**
     * 静态资源加载设置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        return new InternalResourceViewResolver();
    }


}

