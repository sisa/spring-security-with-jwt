package io.sisa.core.model.service.impl;

import io.sisa.core.model.domain.AppUser;
import io.sisa.core.model.domain.Role;
import io.sisa.core.model.repository.ApplicationUserRepository;
import io.sisa.core.model.repository.RoleRepository;
import io.sisa.core.model.repository.UserRoleRepository;
import io.sisa.core.security.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isaozturk
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository,
                                  UserRoleRepository userRoleRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = applicationUserRepository.findByUsername(username);

        if(appUser == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoleRepository.findByUserId(appUser.getId()).forEach(r -> {
            Role role=roleRepository.findOne(r.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        return new JwtUser(appUser.getId(),
                appUser.getUsername(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail(),
                appUser.getPassword(),
                authorities,
                appUser.getLastPasswordResetDate());
    }

    @Transactional
    public void save(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
