package com.taskforge.app.configs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.taskforge.app.utils.LoggerUtil;
import java.util.stream.Collectors;

public class AzureRoleConverter  extends LoggerUtil implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        logger.info("sent jwt token is {}",jwt);
        logger.info(jwt.getClaims().toString());
        // logger.info(jwt);
        List<String> roles = jwt.getClaimAsStringList("scp"); // Extract roles claim

        if (roles == null || roles.isEmpty()) {
            return List.of(); // Return empty if no roles found
        }

        logger.info("Roles extracted: {}", roles);

        return roles.stream()
                .map(role -> "ROLE_" + role) // Prefix roles with "ROLE_"
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

} 
