package kr.co.jeelee.demoboard.global.config;

import kr.co.jeelee.demoboard.global.config.resolver.AllowedFieldSortHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public SortHandlerMethodArgumentResolver sortResolver() {
        return new AllowedFieldSortHandlerMethodArgumentResolver();
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageResolver() {
        return new PageableHandlerMethodArgumentResolver(sortResolver());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageResolver());
    }
}
