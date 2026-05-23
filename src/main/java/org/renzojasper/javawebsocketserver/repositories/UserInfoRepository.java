package org.renzojasper.javawebsocketserver.repositories;

import org.renzojasper.javawebsocketserver.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
