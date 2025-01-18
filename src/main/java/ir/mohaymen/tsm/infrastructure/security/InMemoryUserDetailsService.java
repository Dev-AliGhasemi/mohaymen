package ir.mohaymen.tsm.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    @Value( "${security.users.username}")
    private List<String> usernames;
    @Value( "${security.users.password}")
    private List<String> passwords;

    private final PasswordEncoder passwordEncoder;

    public InMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(username)) {
                return User.withUsername(username)
                        .password(passwordEncoder.encode(passwords.get(i)))
                        .roles("USER")
                        .build();
            }
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
