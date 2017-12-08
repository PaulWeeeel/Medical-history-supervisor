package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:49
 */
public interface UserRepository extends JpaRepository<User, String> {
}
