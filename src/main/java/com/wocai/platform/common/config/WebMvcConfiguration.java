//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wocai.platform.common.config;

import com.wocai.platform.common.interceptor.LoginInterceptor;
import com.wocai.platform.common.resolver.CurrentUserResolver;
import com.wocai.platform.common.resolver.ReqCommonResolver;
import com.wocai.platform.common.util.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(WebMvcConfiguration.class);
    @Value("${spring.resource.static-locations}")
    private String staticLocations;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private CurrentUserResolver currentUserResolver;
    @Autowired
    private ReqCommonResolver reqCommonResolver;

    public WebMvcConfiguration() {
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("respurce:{}", ServerConfig.plat_upload_path);
        registry.addResourceHandler(new String[]{"/**"}).addResourceLocations(new String[]{"file:" + ServerConfig.plat_upload_path + "//", "file:" + ServerConfig.plat_webapp_path + "//"}).addResourceLocations(this.staticLocations.split(","));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginInterceptor).addPathPatterns(new String[]{"/app/**"});
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.currentUserResolver);
        argumentResolvers.add(this.reqCommonResolver);
    }
}
