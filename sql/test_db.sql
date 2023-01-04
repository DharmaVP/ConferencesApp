INSERT INTO role
VALUES (1, 'admin'),
       (2, 'moderator'),
       (3, 'speaker'),
       (4, 'attendee');

CREATE USER 'admin_db'@'localhost' IDENTIFIED BY 'r%g20_Fl8_1';
GRANT ALL ON conference_db.* TO 'admin_db'@'localhost';

INSERT INTO users (email, password, prefix, first_name, last_name, cell_phone, job_title, organisation,
                   role_id)
VALUES ('viktoria@gmail.com', '123', 'Miss', 'Viktoria', 'Trohimenko', '380502222211', 'Analyst', 'EPAM', 1),
       ('anna@epam.com', '445', 'Ms.', 'Anna', 'Sideltseva', '380507772277', 'Acountant', 'EPAM', 2),
       ('olya@yahoo.com', 'qwerty', 'Prof.', 'Olya', 'Sinchuk', '380388855888', 'Scientist', 'SoftServe', 3),
       ('natalia@gmail.com', 'asdfg', 'Mrs.', 'Natalia', 'Kovtsuniak', '380672433544', 'Front-end dev', 'N-ix', DEFAULT),
       ('olena@andersen.com', 'ggg', 'Sister', 'Olena', 'Olena', '380889233521', 'Sales manager', 'Andersen', DEFAULT),
       ('inna@epam.com', '444', 'Miss', 'Inna', 'Beskisko', '380677777777', 'Unemployed', 'EPAM', DEFAULT),
       ('vladyslava@global.com', '21', 'Ms.', 'Vladyslava', 'Repalo', '380688888888', 'Assistant', 'GlobalLogic', DEFAULT);

INSERT INTO event_address (place_id , building , floor , street_number, street_name , city , postal_code , country)
VALUES (DEFAULT, 'Hilton', 2, '109f', 'Shevshenko St.', 'Kyiv', 12345, 'Ukraine'),
       (DEFAULT, 'Hayatt', 5, '21', 'Volodymyrska St.', 'Kyiv', 33333, 'Ukraine'),
       (DEFAULT, 'Parkovy CEC', 3, '19', 'Parkova Av.', 'Kyiv', 77777, 'Ukraine');


INSERT INTO event (event_id, name, description, event_date, visitors, place_id)
VALUES (DEFAULT, 'Java 8', 'talk about Stream API, Lambdas, Optional', '2022-12-25 11:45:00', DEFAULT, 1),
       (DEFAULT, 'Design Patterns', 'Singleton - pattern or antipattern', '2023-01-15 12:00:00', DEFAULT , 1),
       (DEFAULT, 'Clean Code', 'Legacy code, what to do', '2023-01-21 16:30:00', DEFAULT, 2),
       (DEFAULT, 'Hibernate', 'Forget about JDBC', '2023-01-23 14:25:00', DEFAULT, 3),
       (DEFAULT, 'Spring', 'No Servlets!', '2022-12-21 18:00:00', DEFAULT, 3);


INSERT INTO report (report_id, topic, outline, event_id, user_id, accepted)
VALUES (DEFAULT, 'StreamAPI', 'From OOP to functional', 1, NULL, DEFAULT),
       (DEFAULT, 'Lambdas&MethodRef', 'How to use', 1, 3, 1),
       (DEFAULT, 'Optional', 'The problem with Null', 1, NULL, DEFAULT),
       (DEFAULT, 'AbstractFactory', 'The props and cons', 2, NULL, DEFAULT),
       (DEFAULT, 'Observer', 'I seeee you!', 2, NULL, DEFAULT),
       (DEFAULT, 'Wrapper', 'Lets wrap!', 2, NULL, DEFAULT),
       (DEFAULT, 'Builder', 'How to build pizza with builder', 2, NULL, DEFAULT);

-- Errors because of non-unique raws due to unique composite keys --
INSERT INTO event_address (place_id , building , floor , street_number, street_name , city , postal_code , country)
VALUES (DEFAULT, 'Hilton', 2, '109f', 'Shevshenko St.', 'Kyiv', 12345, 'Ukraine');

INSERT INTO event (event_id, name, description, event_date, visitors, place_id)
VALUES (DEFAULT, 'Design Patterns', 'Singletonvdv - pattern or antipattern', '2023-01-15 12:00:00', DEFAULT , 1);

INSERT INTO report (report_id, topic, outline, event_id, user_id, accepted)
VALUES (DEFAULT, 'StreamAPI', 'From OOPdd to functional', 1, NULL, DEFAULT);

-- Participants --
INSERT INTO participant
VALUES (1, 1, 1),
       (1, 2, 0),
       (1, 5, 0),
       (3, 1, 0),
       (3, 4, 0),
       (5, 1, 1),
       (5, 2, 1),
       (2, 1, 0);

