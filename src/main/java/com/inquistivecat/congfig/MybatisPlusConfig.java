package com.inquistivecat.congfig;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mp配置分页插件
 * @author jh
 */
@Configuration
public class MybatisPlusConfig {





    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor intercept = new MybatisPlusInterceptor();
        intercept.addInnerInterceptor(new PaginationInnerInterceptor());
        return intercept;
    }
}
