package coffee.shop.queueservice.controller;

import coffee.shop.queueservice.dto.QueueEntry;
import coffee.shop.queueservice.exception.NotFoundException;
import coffee.shop.queueservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to handle QueueEntry functionalities
 */
@RestController
@RequestMapping("queueEntry")
public class QueueEntryController {
    @Autowired
    private QueueService queueService;

    @PostMapping(value = "/")
    public ResponseEntity<QueueEntry> createQueueEntry(@Valid @RequestBody QueueEntry entry) {
        return new ResponseEntity<>(queueService.createQueueEntry(entry), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<Void> removeQueueEntryOfReservation(@PathVariable long reservationId) throws NotFoundException {
        queueService.removeEntry(reservationId);
        return ResponseEntity.noContent().build();
    }
}
