package com.hmd.typing.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hmd.typing.modules.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	UserEntity findByEmailAndIsLeave(String email, boolean isLeave);

}
