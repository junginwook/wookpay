package com.castle.wookpay.membership.config;

import com.castle.wookpay.common.security.JwtAuthorizationFilter;
import com.castle.wookpay.common.security.JwtTokenProvider;
import com.castle.wookpay.membership.SecurityConfig;
import org.springframework.context.annotation.Import;

@Import({SecurityConfig.class, JwtAuthorizationFilter.class, JwtTokenProvider.class})
public class TestSecurityConfig {

}
