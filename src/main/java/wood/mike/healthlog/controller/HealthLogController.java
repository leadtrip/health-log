package wood.mike.healthlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wood.mike.healthlog.model.HealthLog;
import wood.mike.healthlog.repository.HealthLogRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/logs")
public class HealthLogController {

    private final HealthLogRepository repository;

    public HealthLogController(HealthLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("logs", repository.findAllByOrderByTimestampDesc());
        model.addAttribute("newLog", new HealthLog());
        model.addAttribute("feelings", HealthLog.Feeling.values());
        model.addAttribute("types", HealthLog.EntryType.values());
        return "logs";
    }

    @PostMapping
    public String save(@ModelAttribute HealthLog log) {
        if (log.getTimestamp() == null) {
            log.setTimestamp(LocalDateTime.now());
        }
        repository.save(log);
        return "redirect:/logs";
    }
}
