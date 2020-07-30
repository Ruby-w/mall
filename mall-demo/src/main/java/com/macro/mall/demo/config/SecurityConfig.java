package com.macro.mall.demo.config;

import com.macro.mall.demo.bo.AdminUserDetails;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @ClassName SecurityConfig
 * @Description SpringSecurity的配置
 * @Author AW
 * @Date 2020/7/30 003021:54
 * @Version V1.0
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminMapper umsAdminMapper;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //设置权限
                .antMatchers("/").authenticated() //该路径需要REST角色
                .antMatchers("/**").permitAll()
                .and() //启动基于http的认证
                .httpBasic()
                .realmName("/")
                .and() //配置登录页面
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .and() //配置退出路径
                .logout()
                .logoutSuccessUrl("/")
                .and()  //关闭跨域伪造
                .csrf()
                .disable()
                .headers() //去除X-Frame-Options
                .frameOptions()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UmsAdminExample example = new UmsAdminExample();
                example.createCriteria().andUsernameEqualTo(username);
                List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(example);
                if (umsAdmins != null && umsAdmins.size() > 0) {
                    return new AdminUserDetails(umsAdmins.get(0));
                }
                throw new UsernameNotFoundException("用户名或密码错误!");
            }
        };
    }
}
