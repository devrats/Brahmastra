/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 17-Sep-21
 *   Time: 6:45 PM
 *   File: PricingRepository.java
 */

package com.example.brahmastra.repository;

import com.example.brahmastra.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRepository extends JpaRepository<Pricing,Integer> {
    Pricing findPricingById(int id);
}