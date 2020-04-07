package com.example.demo;

import com.example.demo.models.AuthencationRequest;
import com.example.demo.models.AuthencationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class HelloResource {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private MyUserDetailService myUserDetailService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    @RequestMapping({"/hello"})
//    public String hello(){
//        return "hello";
//    }
//
//    @RequestMapping(value = "/auth",method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthToken(@RequestBody AuthencationRequest authencationRequest)throws Exception{
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authencationRequest.getUsername(),authencationRequest.getPassword())
//        );
//        }catch (BadCredentialsException e){
//            throw  new  Exception("incorrect name or password",e);
//        }
//
//        final UserDetails userDetails=myUserDetailService.loadUserByUsername(authencationRequest.getUsername());
//        final String jwt=jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthencationResponse(jwt));
//    }
//}
