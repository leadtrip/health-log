package wood.mike.healthlog.integration.controller;

import wood.mike.healthlog.integration.AbstractIntegrationTest;
import wood.mike.healthlog.model.HealthLog;
import wood.mike.healthlog.repository.HealthLogRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static wood.mike.healthlog.util.Feeling.GOOD;
import static wood.mike.healthlog.util.EntryType.GENERAL;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthLogRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private HealthLogRepository repository;

    @Test
    void shouldSaveAndRetrieveLog() {
        HealthLog log = new HealthLog();
        log.setFeeling(GOOD);
        log.setType(GENERAL);
        log.setNote("Feeling okay today.");
        log.setTimestamp(LocalDateTime.now());

        repository.save(log);

        var found = repository.findAll();
        assertThat(found).isNotEmpty();
        assertThat(found.getFirst().getNote()).isEqualTo("Feeling okay today.");
    }
}
