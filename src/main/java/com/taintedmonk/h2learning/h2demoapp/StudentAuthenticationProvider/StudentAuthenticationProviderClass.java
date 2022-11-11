package com.taintedmonk.h2learning.h2demoapp.StudentAuthenticationProvider;

import com.taintedmonk.h2learning.h2demoapp.entity.Student;
import com.taintedmonk.h2learning.h2demoapp.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentAuthenticationProviderClass implements AuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(StudentAuthenticationProviderClass.class);

    private StudentRepository repository;

    private PasswordEncoder encoder;

    public StudentAuthenticationProviderClass(StudentRepository repository, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Student student = repository.findBySname(username);
        if (student == null) {
            throw new BadCredentialsException("Details not found");
        }

        if (encoder.matches(password, student.getPassword())) {
            logger.info("Successfully Authenticated the user");
            return new UsernamePasswordAuthenticationToken(username, password, getStudentRoles(student.getSrole()));
        } else {
            throw new BadCredentialsException("Password mismatch");
        }
    }

    private List<GrantedAuthority> getStudentRoles(String studentRoles) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String[] roles = studentRoles.split(",");
        for (String role : roles) {
            logger.info("Role: " + role);
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
        }

        return grantedAuthorityList;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
