package com.astrome.Hogwrats_dashboard.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.astrome.Hogwrats_dashboard.model.Event;
import com.astrome.Hogwrats_dashboard.service.EventService;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LeaderboardController {
    private final EventService service;
    private final CopyOnWriteArrayList<HttpServletResponse> clients = new CopyOnWriteArrayList<>();

    public LeaderboardController(EventService service) {
        this.service = service;
    }

    @PostMapping("/ingest")
    public Map<String,Object> ingest(@RequestBody Event event) {
        service.ingest(event);
        notifyClients();
        return Map.of("ok", true);
    }

    @GetMapping("/leaderboard")
    public Map<String,Integer> leaderboard(@RequestParam(defaultValue = "all") String window) {
        return service.leaderboard(window);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public void stream(HttpServletResponse response) throws IOException {
    response.setContentType("text/event-stream");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("data: {}\n\n"); // send something immediately
    response.getWriter().flush();
    clients.add(response);
    }

    private void notifyClients() {
        for (HttpServletResponse res : clients) {
            try {
                res.getWriter().write("event: update\ndata: {}\n\n");
                res.getWriter().flush();
            } catch (IOException e) {
                clients.remove(res);
            }
        }
    }
}