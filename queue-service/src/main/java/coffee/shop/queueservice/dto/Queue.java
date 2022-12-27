package coffee.shop.queueservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

/**
 * Queue Dto response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Queue {

    private long queueId;
    private long shopId;
    private int maxCustomers;
    private int avgWaitTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<QueueEntry> queueEntries;

    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public int getMaxCustomers() {
        return maxCustomers;
    }

    public void setMaxCustomers(int maxCustomers) {
        this.maxCustomers = maxCustomers;
    }

    public int getAvgWaitTime() {
        return avgWaitTime;
    }

    public void setAvgWaitTime(int avgWaitTime) {
        this.avgWaitTime = avgWaitTime;
    }

    public List<QueueEntry> getQueueEntries() {
        return queueEntries;
    }

    public void setQueueEntries(List<QueueEntry> queueEntries) {
        this.queueEntries = queueEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue queue = (Queue) o;
        return queueId == queue.queueId && shopId == queue.shopId && maxCustomers == queue.maxCustomers && avgWaitTime == queue.avgWaitTime && Objects.equals(queueEntries, queue.queueEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueId, shopId, maxCustomers, avgWaitTime, queueEntries);
    }
}
