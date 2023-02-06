INSERT INTO role
VALUES (1, 'admin'),
       (2, 'moderator'),
       (3, 'speaker'),
       (4, 'attendee');

CREATE USER 'admin_db'@'localhost' IDENTIFIED BY 'r%g20_Fl8_1';
GRANT ALL ON conference_db.* TO 'admin_db'@'localhost';

INSERT INTO users (email, password, prefix, first_name, last_name, cell_phone, job_title, organisation,
                   role_role_id)
VALUES ('viktor@epam.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Mr.', 'Viktor', 'Trohimenko', '380502222211', 'Analyst', 'EPAM', 1),
       ('anna@epam.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Ms.', 'Anna', 'Listenko', '380507772277', 'Acountant', 'EPAM', 2),
       ('olya@softserve.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Prof.', 'Olya', 'Prihodko', '380388855888', 'Scientist', 'SoftServe', 3),
       ('natalia@nix.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Mrs.', 'Natalia', 'Kovtsuniak', '380672433544', 'Front-end dev', 'N-ix',
        DEFAULT),
       ('olena@andersen.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Sister', 'Olena', 'Olena', '380889233521', 'Sales manager', 'Andersen', DEFAULT),
       ('inna@epam.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Miss', 'Inna', 'Roman', '380677777777', 'Unemployed', 'EPAM', DEFAULT),
       ('vladyslava@global.com', '$31$16$xm0kjw-z3EZKu_rS4i-HgMzow7y43mqFfyVol3chivI', 'Ms.', 'Vladyslava', 'Dydenko', '380688888888', 'Assistant', 'GlobalLogic',
        DEFAULT);

INSERT INTO event_address (place_id, building, floor, street_number, street_name, city, postal_code, country)
VALUES (DEFAULT, 'Hilton', 2, '109f', 'Shevshenko St.', 'Kyiv', 12345, 'Ukraine'),
       (DEFAULT, 'Hayatt', 5, '21', 'Volodymyrska St.', 'Kyiv', 33333, 'Ukraine'),
       (DEFAULT, 'Parkovy CEC', 3, '19', 'Parkova Av.', 'Kyiv', 77777, 'Ukraine');


INSERT INTO event (event_id, name, description, event_date, visitors, place_place_id)
VALUES (DEFAULT, 'Java 8', 'talk about Stream API, Lambdas, Optional', '2022-12-25 11:45:00', DEFAULT, 1),
       (DEFAULT, 'Design Patterns', 'Singleton - pattern or antipattern', '2023-01-15 12:00:00', DEFAULT, 1),
       (DEFAULT, 'Clean Code', 'Legacy code, what to do', '2023-01-21 16:30:00', DEFAULT, 2),
       (DEFAULT, 'Hibernate', 'Forget about JDBC', '2023-01-23 14:25:00', DEFAULT, 3),
       (DEFAULT, 'Spring', 'No Servlets!', '2022-12-21 18:00:00', DEFAULT, 3);


INSERT INTO report (report_id, topic, outline, event_event_id, users_user_id, accepted_by_moderator, accepted_by_speaker)
VALUES (DEFAULT, 'StreamAPI', 'From OOP to functional', 1, NULL, DEFAULT, DEFAULT),
       (DEFAULT, 'Lambdas&MethodRef', 'How to use', 1, 3, 1, DEFAULT),
       (DEFAULT, 'Optional', 'The problem with Null', 1, NULL, DEFAULT, DEFAULT),
       (DEFAULT, 'AbstractFactory', 'The props and cons', 2, NULL, DEFAULT, DEFAULT),
       (DEFAULT, 'Observer', 'I seeee you!', 2, NULL, DEFAULT, DEFAULT),
       (DEFAULT, 'Wrapper', 'Lets wrap!', 2, NULL, DEFAULT, DEFAULT),
       (DEFAULT, 'Builder', 'How to build pizza with builder', 2, NULL, DEFAULT, DEFAULT);

-- Errors because of non-unique raws due to unique composite keys --
INSERT INTO event_address (place_id, building, floor, street_number, street_name, city, postal_code, country)
VALUES (DEFAULT, 'Hilton', 2, '109f', 'Shevshenko St.', 'Kyiv', 12345, 'Ukraine');

INSERT INTO event (event_id, name, description, event_date, visitors, place_place_id)
VALUES (DEFAULT, 'Design Patterns', 'Singletonvdv - pattern or antipattern', '2023-01-15 12:00:00', DEFAULT, 1);

INSERT INTO report (report_id, topic, outline, event_event_id, users_user_id, accepted_by_moderator, accepted_by_speaker)
VALUES (DEFAULT, 'StreamAPI', 'From OOPdd to functional', 1, NULL, DEFAULT, DEFAULT);

-- Participants --
INSERT INTO participant
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (3, 1),
       (3, 4),
       (5, 1),
       (5, 2),
       (2, 1);


INSERT INTO event (event_id, name, description, event_date, visitors, place_place_id)
VALUES (DEFAULT, 'Loyalty Programs', 'Main trends of 2023', '2023-04-25 11:45:00', DEFAULT, 1),
       (DEFAULT, 'Fashion trends', 'Whats new', '2023-04-15 12:00:00', DEFAULT, 1),
       (DEFAULT, 'The best marketing ads of 2022', 'We will watch the best ads', '2023-03-21 16:30:00',
        DEFAULT, 2),
       (DEFAULT, 'Fuckup nights', 'Experience of big mistakes', '2023-03-23 14:25:00', DEFAULT, 3),
       (DEFAULT, 'Craft beer', 'IPAs, APAs, Sours, Guses', '2023-03-21 18:00:00', DEFAULT, 3),
       (DEFAULT, 'Drink wine', 'Lets drink', '2023-03-24 18:00:00', DEFAULT, 3),
       (DEFAULT, 'Retailers: how to achieve sales KPI', 'Planning and procrastinating', '2023-03-25 18:00:00', DEFAULT, 3),
       (DEFAULT, 'The union of cats', 'Meowwww!', '2023-03-26 18:00:00', DEFAULT, 3);


