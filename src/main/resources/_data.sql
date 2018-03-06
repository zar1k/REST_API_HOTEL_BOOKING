INSERT INTO room VALUES (10001, 'SINGLE', 2100, 20, 'OCCUPIED');
INSERT INTO room VALUES (10002, 'DELUXE', 4800, 23, 'UNOCCUPIED');
INSERT INTO room VALUES (10003, 'PRESIDENTIAL', 6600, 27, 'UNOCCUPIED');

-- apply table additional_options (
--   id integer generated by default as identity,
--   type_options varchar(255),
--   price double not null,
--   primary key (id)
-- )
--   Hibernate:
--
-- apply table room (
--   id integer generated by default as identity,
--   room_category varchar(255),
--   price double not null,
--   room_number integer,
--   room_status varchar(255),
--   primary key (id)
-- )
--   Hibernate:
--
-- apply table room_options (
--   room_id integer not null,
--   options_id integer not null
-- )