INSERT INTO RESERVATION (RESERVATION_ID, CUSTOMER_ID, TAX, TOTAL_AMOUNT, SHOP_ID, ORDER_TIME, STATUS) VALUES (1, 4, 6, 35, 1, now()::timestamp, 1);
INSERT INTO RESERVATION (RESERVATION_ID, CUSTOMER_ID, TAX, TOTAL_AMOUNT, SHOP_ID, ORDER_TIME, STATUS) VALUES (2, 4, 6, 32.25, 2, now()::timestamp, 1);

INSERT INTO RESERVATION_ITEM (RES_ITEM_ID, RESERVATION_ID, ITEM_ID, QUANTITY) VALUES (1, 1, 1, 2);
INSERT INTO RESERVATION_ITEM (RES_ITEM_ID, RESERVATION_ID, ITEM_ID, QUANTITY) VALUES (2, 1, 3, 1);
INSERT INTO RESERVATION_ITEM (RES_ITEM_ID, RESERVATION_ID, ITEM_ID, QUANTITY) VALUES (3, 2, 4, 1);
