INSERT INTO QUEUE (QUEUE_ID,SHOP_ID,MAX_CUSTOMERS,CUS_AVG_WAIT_TIME) VALUES(1,1,15,2);
INSERT INTO QUEUE (QUEUE_ID,SHOP_ID,MAX_CUSTOMERS,CUS_AVG_WAIT_TIME) VALUES(2,1,15,2);
INSERT INTO QUEUE (QUEUE_ID,SHOP_ID,MAX_CUSTOMERS,CUS_AVG_WAIT_TIME) VALUES(3,2,25,4);

INSERT INTO QUEUE_ENTRY (QUEUE_ID,RESERVATION_ID) VALUES(1,1);
INSERT INTO QUEUE_ENTRY (QUEUE_ID,RESERVATION_ID) VALUES(3,2);
