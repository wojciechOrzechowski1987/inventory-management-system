package com.worzech.inventorymanagementsystem.service.securityUser;

import com.worzech.inventorymanagementsystem.domain.User;
import com.worzech.inventorymanagementsystem.domain.security.SecurityUser;
import com.worzech.inventorymanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

import static com.worzech.inventorymanagementsystem.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;

@Service
@RequiredArgsConstructor
@Transactional
@Qualifier("userDetailService")
public class SecurityUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> supplier = () ->
                new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(supplier);

        return new SecurityUser(user);


    }
}
