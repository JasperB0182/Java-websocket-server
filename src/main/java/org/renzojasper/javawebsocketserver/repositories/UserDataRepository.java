package org.renzojasper.javawebsocketserver.repositories;

import org.renzojasper.javawebsocketserver.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findByUsername(String username);
    UserData findByEmail(String email);
}
