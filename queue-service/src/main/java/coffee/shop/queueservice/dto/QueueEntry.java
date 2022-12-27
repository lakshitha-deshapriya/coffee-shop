package coffee.shop.queueservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * QueueEntry Dto Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueEntry {
    private long queueId;
    private long reservationId;

    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueEntry that = (QueueEntry) o;
        return queueId == that.queueId && reservationId == that.reservationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueId, reservationId);
    }
}
