package coffee.shop.queueservice.mapper;

import coffee.shop.queueservice.dao.QueueDao;
import coffee.shop.queueservice.dao.QueueEntryDao;
import coffee.shop.queueservice.dto.Queue;
import coffee.shop.queueservice.dto.QueueEntry;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapping from Queue Dao to Queue Dto
 */
@Mapper(componentModel = "spring")
public abstract class QueueMapper {
    public abstract Queue mapQueue(QueueDao queueDao);

    public abstract List<Queue> mapQueueList(List<QueueDao> queueDaoList);

    public abstract QueueEntry mapQueueEntry(QueueEntryDao entryDao);
}
