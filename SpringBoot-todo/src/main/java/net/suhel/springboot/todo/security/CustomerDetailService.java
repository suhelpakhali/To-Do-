package net.suhel.springboot.todo.security;

import lombok.AllArgsConstructor;
import net.suhel.springboot.todo.entity.User;
import net.suhel.springboot.todo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerDetailService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not exist with Username or Email"));


        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        System.out.println("Suhel ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// " +user);

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities

        );
    }
}
