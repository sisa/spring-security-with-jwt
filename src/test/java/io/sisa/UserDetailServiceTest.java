package io.sisa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by isaozturk on 17.06.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailServiceTest {

    @Qualifier
    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void loadUserByUsername() throws UsernameNotFoundException {

        final UserDetails userDetails = userDetailsService.loadUserByUsername("sisa");

        assertThat(userDetails).isNotNull();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void usernameNotFoundException() {

        userDetailsService.loadUserByUsername("xxx");

    }

}
