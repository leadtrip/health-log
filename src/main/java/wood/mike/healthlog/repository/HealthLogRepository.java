package wood.mike.healthlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wood.mike.healthlog.model.HealthLog;

import java.util.List;

public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {
    List<HealthLog> findAllByOrderByTimestampDesc();
}

