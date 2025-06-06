package wood.mike.healthlog.unit.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wood.mike.healthlog.model.HealthLog;
import wood.mike.healthlog.repository.HealthLogRepository;
import wood.mike.healthlog.util.EntryType;
import wood.mike.healthlog.util.Feeling;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HealthLogRepositoryTest {

    @Autowired
    private HealthLogRepository repository;

    @Test
    void shouldSaveAndRetrieveLog() {
        HealthLog log = new HealthLog();
        log.setFeeling(Feeling.BAD);
        log.setNote("note");
        log.setType(EntryType.GENERAL);
        repository.save(log);
        List<HealthLog> all = repository.findAll();
        assertThat(all).hasSize(1);
    }
}
