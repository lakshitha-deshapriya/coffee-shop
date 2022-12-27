package coffee.shop.queueservice.controller;

import coffee.shop.queueservice.dto.Queue;
import coffee.shop.queueservice.exception.NotFoundException;
import coffee.shop.queueservice.exception.QueueNotFoundException;
import coffee.shop.queueservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling Queue functionalities
 */
@RestController
@RequestMapping("queues")
public class QueueController {
    @Autowired
    private QueueService queueService;

    @GetMapping(value = "/{queueId}")
    public ResponseEntity<Queue> getQueue(@PathVariable long queueId) throws NotFoundException {
        return ResponseEntity.ok(queueService.getQueueById(queueId));
    }

    @GetMapping(value = "/shop/{shopId}")
    public ResponseEntity<List<Queue>> getQueuesOfShop(@PathVariable long shopId) {
        return ResponseEntity.ok(queueService.getQueuesOfShop(shopId));
    }

    @GetMapping(value = "/available/{shopId}")
    public ResponseEntity<Queue> availableQueueForShop(@PathVariable long shopId ) throws QueueNotFoundException {
        return ResponseEntity.ok(queueService.getBestAvailableQueue( shopId ));
    }
}
