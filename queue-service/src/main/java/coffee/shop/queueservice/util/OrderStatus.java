package coffee.shop.queueservice.util;

public enum OrderStatus {
    CONFIRMED(1),
    IN_QUEUE(2),
    PREPARING(3),
    COMPLETED(4),
    CANCELLED(5),
    ON_HOLD(6);

    private final int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static String getOrderStatus(int status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (status == orderStatus.getStatus()) {
                return orderStatus.name();
            }
        }
        return null;
    }

    public static boolean validateStatus(int status) {
        boolean hasStatus = false;
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (status == orderStatus.getStatus()) {
                hasStatus = true;
                break;
            }
        }
        return hasStatus;
    }
}
