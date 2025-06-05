package wood.mike.healthlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wood.mike.healthlog.model.HealthLog;
import wood.mike.healthlog.repository.HealthLogRepository;
import wood.mike.healthlog.util.EntryType;
import wood.mike.healthlog.util.Feeling;
import wood.mike.healthlog.util.FeelingLabelProvider;

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
        model.addAttribute("feelings", FeelingLabelProvider.getFeelingLabels());
        model.addAttribute("types", EntryType.values());
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

    @GetMapping("/edit/{id}")
    public String editLog(@PathVariable Long id, Model model) {
        HealthLog log = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid log ID: " + id));
        model.addAttribute("newLog", log);
        model.addAttribute("logs", repository.findAll());
        model.addAttribute("feelings", FeelingLabelProvider.getFeelingLabels());
        model.addAttribute("types", EntryType.values());
        return "logs";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLog(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/logs";
    }

    @PostMapping("/logs")
    public String saveLog(@ModelAttribute HealthLog log) {
        repository.save(log);
        return "redirect:/logs";
    }

}
