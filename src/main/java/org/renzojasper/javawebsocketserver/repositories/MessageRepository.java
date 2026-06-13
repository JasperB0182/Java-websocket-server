package org.renzojasper.javawebsocketserver.repositories;

import org.renzojasper.javawebsocketserver.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop50ByOrderByMessageIDAsc();

}
