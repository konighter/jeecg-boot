package com.hanzhan.tech.workbench.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = {"com.hanzhan.tech.**.mapper*"})
public class MybatisPlusConfig {
}
