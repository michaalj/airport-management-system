INSERT INTO PERSON (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES
                                                           ('Anna', 'Nowak', '1990-04-12'),
                                                           ('Jan', 'Kowalski', '1985-07-01'),
                                                           ('Maria', 'Wiśniewska', '1995-03-23'),
                                                           ('Piotr', 'Zieliński', '1992-11-09'),
                                                           ('Alicja', 'Dąbrowska', '1988-12-31'),
                                                           ('Katarzyna', 'Lewandowska', '1993-01-15'),
                                                           ('Tomasz', 'Wójcik', '1980-05-20'),
                                                           ('Magdalena', 'Kamińska', '1998-09-03'),
                                                           ('Krzysztof', 'Kowalczyk', '1975-11-25'),
                                                           ('Ewa', 'Zając', '2000-03-10'),
                                                           ('Marcin', 'Mazur', '1982-07-19'),
                                                           ('Joanna', 'Król', '1991-12-01'),
                                                           ('Andrzej', 'Pawlak', '1978-02-28'),
                                                           ('Monika', 'Szymańska', '1996-06-08'),
                                                           ('Grzegorz', 'Walczak', '1989-10-17'),
                                                           ('Barbara', 'Duda', '1994-08-05'),
                                                           ('Adam', 'Głowacki', '1983-04-22'),
                                                           ('Karolina', 'Sikora', '1999-07-30'),
                                                           ('Michał', 'Baran', '1977-01-09'),
                                                           ('Natalia', 'Rutkowska', '1990-06-14'),
                                                           ('Szymon', 'Piotrowski', '1995-02-18'),
                                                           ('Patrycja', 'Jankowska', '1997-11-03'),
                                                           ('Łukasz', 'Grabowski', '1988-08-21');

INSERT INTO EMPLOYEE (PESEL, SALARY, START_DATE, END_DATE, PERSON_ID) VALUES
('90041212345', 4800.00, '2020-01-01', '2023-12-31', 1),
('85070123456', 5200.00, '2019-05-10', NULL, 2),
('95032334567', 6100.00, '2022-03-01', NULL, 3),
('92110945678', 5500.00, '2021-08-15', NULL, 4),
('88123156789', 5800.00, '2020-10-01', NULL, 5),
('93011567890', 5000.00, '2023-02-20', NULL, 6),
('80052078901', 6500.00, '2018-07-01', NULL, 7),
('98090389012', 4900.00, '2022-09-01', NULL, 8);

INSERT INTO PASSENGER (IDENTITY_DOCUMENT, PERSON_ID) VALUES
                                                         (100000001, 1),
                                                         (100000002, 2),
                                                         (100000003, 3),
                                                         (100000004, 4),
                                                         (100000005, 5),
                                                         (100000006, 6),
                                                         (100000007, 7),
                                                         (100000008, 8),
                                                         (100000009, 9),
                                                         (100000010, 10),
                                                         (100000011, 11),
                                                         (100000012, 12),
                                                         (100000013, 13),
                                                         (100000014, 14),
                                                         (100000015, 15),
                                                         (100000016, 16),
                                                         (100000017, 17),
                                                         (100000018, 18),
                                                         (100000019, 19),
                                                         (100000020, 20),
                                                         (100000021, 21),
                                                         (100000022, 22),
                                                         (100000023, 23);

INSERT INTO RESERVATION (RESERVATION_NUMBER) VALUES
                                                 ('RES-F1-001'), ('RES-F1-002'), ('RES-F1-003'), ('RES-F1-004'),
                                                 ('RES-F2-001'), ('RES-F2-002'), ('RES-F2-003'), ('RES-F2-004'),
                                                 ('RES-F3-001'), ('RES-F3-002'), ('RES-F3-003'), ('RES-F3-004'),
                                                 ('RES-F4-001'), ('RES-F4-002'), ('RES-F4-003'), ('RES-F4-004'),
                                                 ('RES-F5-001'), ('RES-F5-002'), ('RES-F5-003'), ('RES-F5-004'),
                                                 ('RES-F6-001'), ('RES-F6-002'),
                                                 ('RES-F5-005'),
                                                 ('RES-F4-005');

INSERT INTO PASSENGER_RESERVATION (PASSENGER_ID, RESERVATION_ID) VALUES
                                                                     (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
                                                                     (11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16), (17, 17), (18, 18), (19, 19), (20, 20),
                                                                     (21, 21), (22, 22),
                                                                     (1, 23),
                                                                     (2, 24);

INSERT INTO CHECK_IN (RESERVATION_NUMBER, STATUS, PASSENGER_ID) VALUES
                                                                    ('RES-F1-001', 'NOT_COMPLETED', 1),
                                                                    ('RES-F1-002', 'NOT_COMPLETED', 2),
                                                                    ('RES-F1-003', 'NOT_COMPLETED', 3),
                                                                    ('RES-F1-004', 'NOT_COMPLETED', 4),
                                                                    ('RES-F2-001', 'NOT_COMPLETED', 5),
                                                                    ('RES-F2-002', 'NOT_COMPLETED', 6),
                                                                    ('RES-F2-003', 'NOT_COMPLETED', 7),
                                                                    ('RES-F2-004', 'NOT_COMPLETED', 8),
                                                                    ('RES-F3-001', 'NOT_COMPLETED', 9),
                                                                    ('RES-F3-002', 'NOT_COMPLETED', 10),
                                                                    ('RES-F3-003', 'NOT_COMPLETED', 11),
                                                                    ('RES-F3-004', 'NOT_COMPLETED', 12),
                                                                    ('RES-F4-001', 'NOT_COMPLETED', 13),
                                                                    ('RES-F4-002', 'NOT_COMPLETED', 14),
                                                                    ('RES-F4-003', 'NOT_COMPLETED', 15),
                                                                    ('RES-F4-004', 'NOT_COMPLETED', 16),
                                                                    ('RES-F5-001', 'NOT_COMPLETED', 17),
                                                                    ('RES-F5-002', 'NOT_COMPLETED', 18),
                                                                    ('RES-F5-003', 'NOT_COMPLETED', 19),
                                                                    ('RES-F5-004', 'NOT_COMPLETED', 20),
                                                                    ('RES-F6-001', 'NOT_COMPLETED', 21),
                                                                    ('RES-F6-002', 'NOT_COMPLETED', 22),
                                                                    ('RES-F5-005', 'NOT_COMPLETED', 1),
                                                                    ('RES-F4-005', 'NOT_COMPLETED', 2);
INSERT INTO BAGGAGE (CODE, SIZE, RESERVATION_ID) VALUES
                                                     ('BAG-001', 'CABIN', 1), ('BAG-002', 'REGISTERED', 2), ('BAG-003', 'CABIN', 3), ('BAG-004', 'REGISTERED', 4),
                                                     ('BAG-005', 'CABIN', 5), ('BAG-006', 'REGISTERED', 6), ('BAG-007', 'CABIN', 7), ('BAG-008', 'REGISTERED', 8),
                                                     ('BAG-009', 'CABIN', 9), ('BAG-010', 'REGISTERED', 10), ('BAG-011', 'CABIN', 11), ('BAG-012', 'REGISTERED', 12),
                                                     ('BAG-013', 'CABIN', 13), ('BAG-014', 'REGISTERED', 14), ('BAG-015', 'CABIN', 15), ('BAG-016', 'REGISTERED', 16),
                                                     ('BAG-017', 'CABIN', 17), ('BAG-018', 'REGISTERED', 18), ('BAG-019', 'CABIN', 19), ('BAG-020', 'REGISTERED', 20),
                                                     ('BAG-021', 'CABIN', 21), ('BAG-022', 'CABIN', 22),
                                                     ('BAG-023', 'REGISTERED', 23),
                                                     ('BAG-024', 'REGISTERED', 24);

INSERT INTO TECHNICAL_SUPPORT (ID, STATUS) VALUES
                                               (4, 'AVAILABLE'),
                                               (5, 'AVAILABLE'),
                                               (6, 'AVAILABLE'),
                                               (7, 'AVAILABLE'),
                                               (8, 'AVAILABLE');

INSERT INTO PLANE (REGISTRATION_NUMBER, MODEL, CAPACITY) VALUES
                                                             ('SP-AAA1', 'Boeing 737', 180), ('SP-BBB2', 'Airbus A320', 160), ('SP-CCC3', 'Boeing 777', 300),
                                                             ('SP-DDD4', 'Embraer 190', 100), ('SP-EEE5', 'ATR 72', 70), ('SP-FFF6', 'Boeing 787', 250),
                                                             ('SP-GGG7', 'Airbus A321', 200), ('SP-HHH8', 'Boeing 747', 350), ('SP-III9', 'CRJ 900', 90),
                                                             ('SP-JJJ0', 'Airbus A220', 130);

INSERT INTO MAINTENANCE_HISTORY (PLANE_ID, TECHNICAL_SUPPORT_ID, INSPECTION_NAME, FROM_DATE, TO_DATE) VALUES
                                                                                                          (1, 4, 'Engine Check A', '2024-01-01', '2024-01-02'),
                                                                                                          (2, 5, 'Landing Gear Check', '2024-02-10', '2024-02-12'),
                                                                                                          (3, 6, 'Routine Check B', '2024-03-01', '2024-03-03'),
                                                                                                          (4, 7, 'Engine Replacement', '2024-04-05', '2024-04-07'),
                                                                                                          (5, 8, 'Avionics Update', '2024-05-15', '2024-05-16');

INSERT INTO ENGINE (TYPE, PLANE_ID) VALUES
                                        ('CFM56', 1), ('CFM56', 1), ('V2500', 2), ('V2500', 2), ('GE90', 3), ('GE90', 3),
                                        ('CF34', 4), ('CF34', 4), ('PW127', 5), ('PW127', 5), ('GEnx', 6), ('GEnx', 6),
                                        ('LEAP-1A', 7), ('LEAP-1A', 7), ('RB211', 8), ('RB211', 8), ('CF34-8C5', 9), ('CF34-8C5', 9),
                                        ('PW1500G', 10), ('PW1500G', 10);

INSERT INTO TERMINAL (number) VALUES ('T1'), ('T2');

INSERT INTO GATE (number, status) VALUES
                                      ('G1', 'AVAILABLE'), ('G2', 'AVAILABLE'), ('G3', 'AVAILABLE'), ('G4', 'AVAILABLE');

INSERT INTO TERMINAL_GATE (terminal_id, gate_id) VALUES
                                                     (1, 1), (1, 2), (2, 3), (2, 4);

INSERT INTO FLIGHT (FLIGHT_NUMBER, AIRLINE, ARRIVAL_AIRPORT, DEPARTURE_TIME, ARRIVAL_TIME) VALUES
                                                                                               ('LO123', 'LOT', 'LHR', '2025-07-01T10:00:00', '2025-07-01T12:00:00'),
                                                                                               ('LH456', 'Lufthansa', 'MUN', '2025-07-02T08:30:00', '2025-07-02T10:10:00'),
                                                                                               ('AF789', 'Air France', 'CDG', '2025-07-03T14:00:00', '2025-07-03T16:10:00'),
                                                                                               ('BA001', 'British Airways', 'LHR', '2025-07-04T15:00:00', '2025-07-04T17:10:00'),
                                                                                               ('KLM88', 'KLM', 'AMS', '2025-07-05T12:00:00', '2025-07-05T14:15:00'),
                                                                                               ('RYR101', 'Ryanair', 'STN', '2025-07-06T06:00:00', '2025-07-06T07:30:00'),
                                                                                               ('WIZ202', 'WizzAir', 'LTN', '2025-07-07T18:00:00', '2025-07-07T19:45:00');

INSERT INTO RESERVATION_FLIGHTS (RESERVATION_ID, FLIGHT_ID) VALUES
                                                                (1, 1), (2, 1), (3, 1), (4, 1),
                                                                (5, 2), (6, 2), (7, 2), (8, 2),
                                                                (9, 3), (10, 3), (11, 3), (12, 3),
                                                                (13, 4), (14, 4), (15, 4), (16, 4),
                                                                (17, 5), (18, 5), (19, 5), (20, 5),
                                                                (21, 6), (22, 6),
                                                                (23, 5),
                                                                (24, 4);

INSERT INTO FLIGHT_PLANE (FLIGHT_ID, PLANE_ID) VALUES
                                                   (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7);

INSERT INTO FLIGHT_GATE (FLIGHT_ID, GATE_ID) VALUES
                                                 (1, 1),
                                                 (2, 2),
                                                 (3, 1),
                                                 (4, 2),
                                                 (5, 1),
                                                 (6, 3),
                                                 (7, 4);

INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 1);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 2);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 3);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 4);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 5);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 6);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 7);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 8);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 9);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 10);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 11);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 12);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 13);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 14);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 15);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 16);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 24);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 17);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 18);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 19);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 20);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (1, 23);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (2, 21);
INSERT INTO TERMINAL_CHECKIN (TERMINAL_ID, CHECKIN_ID) VALUES (2, 22);

INSERT INTO CHECKIN_EMPLOYEE (CHECKIN_ID, EMPLOYEE_ID) VALUES (1, 2);
INSERT INTO CHECKIN_EMPLOYEE (CHECKIN_ID, EMPLOYEE_ID) VALUES (2, 2);
INSERT INTO CHECKIN_EMPLOYEE (CHECKIN_ID, EMPLOYEE_ID) VALUES (3, 2);
INSERT INTO CHECKIN_EMPLOYEE (CHECKIN_ID, EMPLOYEE_ID) VALUES (4, 2);

INSERT INTO TERMINAL_CHECKIN_DESKS (terminal_id, checkin_desk) VALUES
                                                                   (1, 'A1'), (1, 'A2'), (2, 'B1'), (2, 'B2');

INSERT INTO TERMINAL_AIRLINES (terminal_id, airline) VALUES
                                                         (1, 'LOT'), (1, 'Lufthansa'), (2, 'Ryanair'), (2, 'WizzAir');