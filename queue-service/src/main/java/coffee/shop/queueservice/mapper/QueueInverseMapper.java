package coffee.shop.queueservice.mapper;

import coffee.shop.queueservice.dao.QueueEntryDao;
import coffee.shop.queueservice.dto.QueueEntry;
import org.mapstruct.Mapper;

/**
 * Mapping from Queue Dto to Queue Dao
 */
@Mapper(componentModel = "spring")
public abstract class QueueInverseMapper {
    public abstract QueueEntryDao mapQueueEntry(QueueEntry queueDao);
}
