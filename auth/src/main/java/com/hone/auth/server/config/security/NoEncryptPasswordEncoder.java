package com.hone.auth.server.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>没有加密密码编码器</p>
 * @Author hourz
 * @since 2019-07-16
 */
public class NoEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals((String) charSequence);
    }
}
