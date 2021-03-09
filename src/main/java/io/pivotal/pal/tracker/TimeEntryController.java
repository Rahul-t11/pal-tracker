package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntryRepository = timeEntriesRepo;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return created(null)
                .body(timeEntryRepository.create(timeEntryToCreate));
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        var timeEntryFound = timeEntryRepository.find(id);

        if (timeEntryFound==null){
            return notFound().build();
        }
        else {
            return ok(timeEntryFound);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ok(timeEntryRepository.list());
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        var timeEntryUpdated = timeEntryRepository.update(id, timeEntry);

        if (timeEntryUpdated ==null ){
            return notFound().build();
        }
        else {
            return ok(timeEntryUpdated);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return noContent().build();
    }
}
