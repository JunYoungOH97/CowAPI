package server.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.server.Domain.Entity.Dashboard;

import java.util.Optional;

public interface DashboardRepository extends JpaRepository<Dashboard, String> {
    Optional<Dashboard> findByName(String name);
}
