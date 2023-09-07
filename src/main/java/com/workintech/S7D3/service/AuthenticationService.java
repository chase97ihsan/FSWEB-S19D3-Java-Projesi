package com.workintech.S7D3.service;

import com.workintech.S7D3.dao.MemberRepository;
import com.workintech.S7D3.dao.RoleRepository;
import com.workintech.S7D3.dto.LoginResponse;
import com.workintech.S7D3.entity.Member;
import com.workintech.S7D3.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthenticationService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    //Passwordü encoded etmemizin sebebi veri tabanında şifre açık bir şekilde deişre olmasın...
    public Member register(String email,String password){
        Optional<Member> optional=memberRepository.findMemberByEmail(email);
        if(optional.isPresent()){
            return null;
            //Throw  exception
        }
        String encodedPassword=passwordEncoder.encode(password);
        Role role=roleRepository.findByAuthority("USER").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        Member member=new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setAuthorities(roles);
        return memberRepository.save(member);
    }
    public LoginResponse login(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwtToken(auth);
            return new LoginResponse(memberRepository.findMemberByEmail(email).get(), token);
        } catch (AuthenticationException ex) {
            return new LoginResponse(null, "");
        }
    }
    public void delete(){
         memberRepository.deleteAll();
         roleRepository.deleteAll();
    }

}
