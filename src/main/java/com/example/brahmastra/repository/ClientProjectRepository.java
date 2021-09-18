/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 18-Sep-21
 *   Time: 4:25 PM
 *   File: ClientProjectRepository.java
 */

package com.example.brahmastra.repository;

import com.example.brahmastra.entity.ClientProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProjectRepository extends JpaRepository<ClientProject,Integer> {
}