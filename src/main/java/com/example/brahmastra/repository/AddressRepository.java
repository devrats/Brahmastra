/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 18-Sep-21
 *   Time: 2:34 PM
 *   File: AddressRepository.java
 */

package com.example.brahmastra.repository;

import com.example.brahmastra.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}