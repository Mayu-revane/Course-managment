package com.courseMangemnet.course.Service;

import com.courseMangemnet.course.entity.UserModel;
import com.courseMangemnet.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserModel user = userRepository.findByUserName(username)  // ✅ Use correct method and class
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return new CustomUserDetails(user);
        }
}


