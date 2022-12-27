package coffee.shop.queueservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "queue")
public class QueueDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "queue_seq")
    @GenericGenerator(name = "queue_seq", strategy="increment")
    @Column(name = "queue_id", nullable = false)
    private Long queueId;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "max_customers")
    private Integer maxCustomers;

    @Column(name = "cus_avg_wait_time")
    private Integer avgWaitTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "queue", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<QueueEntryDao> queueEntries;

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getMaxCustomers() {
        return maxCustomers;
    }

    public void setMaxCustomers(Integer maxCustomers) {
        this.maxCustomers = maxCustomers;
    }

    public Integer getAvgWaitTime() {
        return avgWaitTime;
    }

    public void setAvgWaitTime(Integer avgWaitTime) {
        this.avgWaitTime = avgWaitTime;
    }

    public List<QueueEntryDao> getQueueEntries() {
        return queueEntries;
    }

    public void setQueueEntries(List<QueueEntryDao> queueEntries) {
        this.queueEntries = queueEntries;
    }
}
