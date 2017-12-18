package io.sisa.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author isaozturk
 */

public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    @Getter
    @Setter
    private final String firstName;
    @Getter
    @Setter
    private final String lastName;
    @JsonIgnore
    @Getter
    @Setter
    private final String password;
    @Getter
    @Setter
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    @Getter
    @Setter
    private final boolean enabled;
    @JsonIgnore
    @Getter
    @Setter
    private final Date lastPasswordResetDate;

    public JwtUser(
            Long id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = true;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
