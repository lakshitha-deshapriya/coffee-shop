INSERT INTO SHOP (SHOP_ID, NAME, LONGITUDE, LATITUDE, ADDRESS, CONTACT_NO, NO_OF_QUEUES, QUEUE_SIZE, OPEN_TIME, CLOSE_TIME, CREATED_BY, LAST_MODIFIED)
            VALUES (1, 'The Coffee Bean & Tea Leaf', 6.9187, 79.8635, '07, 2 Maitland Cres, Colombo 07', 0112676167, 2, 10, '10:00', '20:00', 2, now()::timestamp );
INSERT INTO SHOP (SHOP_ID, NAME, LONGITUDE, LATITUDE, ADDRESS, CONTACT_NO, NO_OF_QUEUES, QUEUE_SIZE, OPEN_TIME, CLOSE_TIME, CREATED_BY, LAST_MODIFIED)
            VALUES (2, 'Barista', 6.9115, 79.9008, 'No 55A, Rajagiriya, Buthgamuwa Rd, Colombo 10', 0112875120, 1, 15, '10:00', '23:00', 1, now()::timestamp );

INSERT INTO MENU (MENU_ID, SHOP_ID, NAME, DESCRIPTION, ACTIVE, LAST_MODIFIED) VALUES (1, 1, 'Morning Menu', 'Menu for the Morning', 'y', now()::timestamp);
INSERT INTO MENU (MENU_ID, SHOP_ID, NAME, DESCRIPTION, ACTIVE, LAST_MODIFIED) VALUES (2, 1, 'Evening Menu', 'Menu for the Evening', 'y', now()::timestamp);
INSERT INTO MENU (MENU_ID, SHOP_ID, NAME, DESCRIPTION, ACTIVE, LAST_MODIFIED) VALUES (3, 2, 'November Menu', 'Menu for the Month November', 'n', now()::timestamp);
INSERT INTO MENU (MENU_ID, SHOP_ID, NAME, DESCRIPTION, ACTIVE, LAST_MODIFIED) VALUES (4, 2, 'December Menu', 'Menu for the Month December', 'y', now()::timestamp);

INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (1, 1, 'Black', 12.5, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (2, 1, 'Espresso', 15.22, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (3, 1, 'Macchiato', 10, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (4, 2, 'Affogato', 12.0, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (5, 2, 'Mocha', 13.0, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (6, 2, 'Iced Coffee', 8.75, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (7, 3, 'Red Eye', 13.2, 'n', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (8, 3, 'Black', 8.75, 'n', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (9, 4, 'Flat White', 20.0, 'y', now()::timestamp);
INSERT INTO MENU_ITEM (ITEM_ID, MENU_ID, NAME, PRICE, AVAILABLE, LAST_MODIFIED) VALUES (10, 4, 'Black Coffee', 10.75, 'y', now()::timestamp);



