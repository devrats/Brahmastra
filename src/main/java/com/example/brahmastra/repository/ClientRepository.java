/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 12:00 PM
 *   File: ClientRepository.java
 */

package com.example.brahmastra.repository;

import com.example.brahmastra.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    public Client findClientByUsername(String username);
}