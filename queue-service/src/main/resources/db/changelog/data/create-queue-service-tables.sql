CREATE TABLE QUEUE (
            QUEUE_ID            SERIAL NOT NULL PRIMARY KEY,
            SHOP_ID             Int4 NOT NULL,
            MAX_CUSTOMERS       Int4,
            CUS_AVG_WAIT_TIME   NUMERIC(3));

COMMENT ON TABLE QUEUE IS 'Queue of a shop';
COMMENT ON COLUMN QUEUE.QUEUE_ID IS 'Queue Id';
COMMENT ON COLUMN QUEUE.SHOP_ID IS 'Shop Id of a Queue';
COMMENT ON COLUMN QUEUE.MAX_CUSTOMERS IS 'Maximum number of customers per queue';
COMMENT ON COLUMN QUEUE.CUS_AVG_WAIT_TIME IS 'Avg wait time in queue per customer in minutes';

CREATE TABLE QUEUE_ENTRY (
            QUEUE_ID            Int4 NOT NULL,
            RESERVATION_ID      Int4 NOT NULL,
            PRIMARY KEY(QUEUE_ID, RESERVATION_ID));

COMMENT ON TABLE QUEUE_ENTRY IS 'Entry of a queue';
COMMENT ON COLUMN QUEUE_ENTRY.QUEUE_ID IS 'Queue Id';
COMMENT ON COLUMN QUEUE_ENTRY.RESERVATION_ID IS 'Reservation id of the queue entry';

ALTER TABLE QUEUE_ENTRY ADD CONSTRAINT FK_QUEUE_ENTRY_QUEUE FOREIGN KEY (QUEUE_ID) REFERENCES QUEUE (QUEUE_ID);
ALTER TABLE QUEUE_ENTRY ADD CONSTRAINT FK_QUEUE_ENTRY_RESERVATION FOREIGN KEY (RESERVATION_ID) REFERENCES RESERVATION (RESERVATION_ID);
