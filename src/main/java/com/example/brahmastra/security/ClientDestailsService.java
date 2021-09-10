/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 8:53 PM
 *   File: PublicDestailsService.java
 */

package com.example.brahmastra.security;

import com.example.brahmastra.entity.Client;
import com.example.brahmastra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ClientDestailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client clientByUserName = clientRepository.findClientByUsername(s);
        if (clientByUserName == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        ClientDetail clientDetail = new ClientDetail(clientByUserName);
        return clientDetail;
    }
}