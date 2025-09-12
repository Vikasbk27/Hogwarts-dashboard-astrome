package com.astrome.Hogwrats_dashboard.service;

import org.springframework.stereotype.Service;

import com.astrome.Hogwrats_dashboard.model.Event;
import com.astrome.Hogwrats_dashboard.repository.EventRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService {
    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public void ingest(Event e) {
        repo.save(e);
    }

    public Map<String, Integer> leaderboard(String window) {
        Instant since = null;
        if ("5m".equals(window)) since = Instant.now().minus(5, ChronoUnit.MINUTES);
        else if ("1h".equals(window)) since = Instant.now().minus(1, ChronoUnit.HOURS);

        List<Object[]> rows = (since == null) ? repo.findTotalsAll() : repo.findTotalsSince(since);

        Map<String, Integer> totals = new HashMap<>();
        for (String house : new String[]{"Gryff", "Slyth", "Raven", "Huff"}) {
            totals.put(house, 0);
        }
        for (Object[] row : rows) {
            totals.put((String) row[0], ((Number) row[1]).intValue());
        }
        return totals;
    }
}