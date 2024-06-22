package com.peach.hangnag.common.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.peach.hangnag"])
@Configuration
class FeignConfig
