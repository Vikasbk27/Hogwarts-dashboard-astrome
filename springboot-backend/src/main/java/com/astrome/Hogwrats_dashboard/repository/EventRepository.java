package com.astrome.Hogwrats_dashboard.repository;



import com.astrome.Hogwrats_dashboard.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e.category, SUM(e.points) FROM Event e WHERE e.timestamp >= :since GROUP BY e.category")
    List<Object[]> findTotalsSince(Instant since);

    @Query("SELECT e.category, SUM(e.points) FROM Event e GROUP BY e.category")
    List<Object[]> findTotalsAll();
}
