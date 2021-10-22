package com.maif.fangfarm.api.v1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.core.property.FangFarmApiProperty;

@RestController
@RequestMapping(path = "/v1/tokens", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {
	
	@Autowired
	private FangFarmApiProperty fangFarmApiProperty;
	

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(fangFarmApiProperty.getSeguranca().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.SC_NO_CONTENT);
	}
	
}
