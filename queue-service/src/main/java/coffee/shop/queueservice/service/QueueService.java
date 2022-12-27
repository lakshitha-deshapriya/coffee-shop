package coffee.shop.queueservice.service;

import coffee.shop.queueservice.dto.Queue;
import coffee.shop.queueservice.dto.QueueEntry;
import coffee.shop.queueservice.exception.NotFoundException;
import coffee.shop.queueservice.exception.QueueNotFoundException;

import java.util.List;

public interface QueueService {
    Queue getQueueById(long queueId) throws NotFoundException;

    List<Queue> getQueuesOfShop(long shopId);

    Queue getBestAvailableQueue(long shopId) throws QueueNotFoundException;

    QueueEntry createQueueEntry(QueueEntry entry);

    void removeEntry(long reservationId) throws NotFoundException;
}
