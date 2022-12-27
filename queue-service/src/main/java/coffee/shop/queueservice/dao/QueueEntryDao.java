package coffee.shop.queueservice.dao;

import javax.persistence.*;

@Entity
@Table(name = "queue_entry")
@IdClass(QueueEntryIdClass.class)
public class QueueEntryDao {
    @Id
    @Column(name = "queue_id", nullable = false)
    private Long queueId;

    @Id
    @Column(name = "reservation_id", nullable = false)
    private Long reservationId;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", nullable = false, insertable = false, updatable = false)
    private QueueDao queue;

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public QueueDao getQueue() {
        return queue;
    }

    public void setQueue(QueueDao queue) {
        this.queue = queue;
    }
}
