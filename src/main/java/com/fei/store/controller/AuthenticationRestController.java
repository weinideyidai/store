package com.fei.store.controller;

import com.fei.store.security.JwtTokenUtil;
import com.fei.store.security.domain.AuthenticationException;
import com.fei.store.security.domain.JwtAuthenticationRequest;
import com.fei.store.security.domain.JwtAuthenticationResponse;
import com.fei.store.security.domain.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 授权控制器
 *
 *
 */
@RestController
public class AuthenticationRestController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserService")
  private UserDetailsService userDetailsService;

  /**
   * 创建授权令牌 (登录)
   *
   * @param authenticationRequest
   * @return
   */
  @RequestMapping(value = "${jwt.route.authentication.path}")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
    //校验用户名密码是否正确
    Authentication authenticate = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authenticate);
    final String token = jwtTokenUtil.generateJwtToken(authenticate);
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }


  private Authentication authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      //校验用户名密码是否正确
      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return authenticate;
    } catch (DisabledException e) {
      throw new AuthenticationException("账户不可用", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("坏的凭据!", e);
    }
  }

}
