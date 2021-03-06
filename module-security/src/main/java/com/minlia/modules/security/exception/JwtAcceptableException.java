package com.minlia.modules.security.exception;

import com.minlia.modules.security.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

public class JwtAcceptableException extends AuthenticationException {
    private static final long serialVersionUID = -5959543783324224864L;

    private JwtToken token;

    public JwtAcceptableException(String msg) {
        super(msg);
    }

    public JwtAcceptableException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
