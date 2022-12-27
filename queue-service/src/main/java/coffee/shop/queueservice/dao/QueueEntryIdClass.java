package coffee.shop.queueservice.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class QueueEntryIdClass implements Serializable {
    @Id
    @Column(name = "queue_id", nullable = false)
    private long queueId;

    @Id
    @Column(name = "reservation_id", nullable = false)
    private long reservationId;

    public QueueEntryIdClass() {
    }

    public QueueEntryIdClass(long queueId, long reservationId) {
        this.queueId = queueId;
        this.reservationId = reservationId;
    }

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
        QueueEntryIdClass idClass = (QueueEntryIdClass) o;
        return queueId == idClass.queueId && reservationId == idClass.reservationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueId, reservationId);
    }
}
