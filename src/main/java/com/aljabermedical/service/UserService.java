package com.aljabermedical.service;

import com.aljabermedical.models.Menu;
import com.aljabermedical.models.Role;
import com.aljabermedical.models.User;
import com.aljabermedical.repository.MenuRepository;
import com.aljabermedical.repository.UserRepository;
import com.aljabermedical.security.jwt.JwtUtils;
import com.aljabermedical.payload.responsedto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    public String generateToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return jwt;
    }

    public UserResponse getUser() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

//        String username = jwtUtils.getUserNameFromJwtToken(jwtRequest.getToken());
        User user = userRepository.findByUsername(username).get();
        Set<Role> userRoles = user.getRoles();
        List<String> roles = userRoles.stream().map(i -> i.getName().name()).collect(Collectors.toList());

        Set<Menu> menus = new HashSet<>();
        for (Role role : userRoles) {
            menus.addAll(menuRepository.findAllByRole(role));
        }

        UserResponse userResponse = UserResponse
                .builder()
                .id(user.getId())
                .username(username)
                .email(user.getEmail())
                .roles(roles)
                .menus(menus)
                .fullName(user.getFullName())
                .mobileNo(user.getMobileNo())
                .occupation(user.getOccupation())
                .build();

        return userResponse;
    }

//    String getUsername(String username, String password) {
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(jwt);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
//
//        Set<Role> userRoles = userRepository.findByUsername(userDetails.getUsername()).get().getRoles();
//
//        Set<Menu> menus = new HashSet<>();
//        for (Role role : userRoles) {
//            menus.addAll(menuRepository.findAllByRole(role));
//        }
//    }
}
