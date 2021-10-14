package ru.raiffeisen.shopwarehouse.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.raiffeisen.shopwarehouse.entity.Role;
import ru.raiffeisen.shopwarehouse.entity.User;
import ru.raiffeisen.shopwarehouse.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean create(User user) {
        userRepository.save(user);
        if(userRepository.getById(user.getId()).equals(user)) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(long id) {
        return userRepository.getUserById(id);
    }

    public boolean update(User user, long id) {
        User localUser = userRepository.getUserById(id);
        if(!localUser.equals(null)) {
            user.setId(id);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(long id) {
        User user = userRepository.getUserById(id);
        userRepository.delete(user);
        user = userRepository.getUserById(id);
        if(user == null) {
            return true;
        } else {
            return false;
        }
    }

    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(s);
        boolean enabled = user.isActivate();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user.getRoles())
        );
    }
    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> roles) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    private final Collection<? extends GrantedAuthority> getAuthorities(
            final Collection<Role> roles) {
        return getGrantedAuthorities(roles.stream().map(Role::getName).collect(Collectors.toList()));
    }
}
