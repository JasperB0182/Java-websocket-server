package org.renzojasper.javawebsocketserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 3600)
public class SessionConfig {
}
