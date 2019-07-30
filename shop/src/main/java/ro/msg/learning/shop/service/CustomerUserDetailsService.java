package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.ICustomerRepository;

@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

        private ICustomerRepository customerRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
            Customer customer = customerRepository.findByUsername(username);
            if (customer == null) {
                throw new  UsernameNotFoundException(username + "' not found!");
            }

            return customer;
    }
}
