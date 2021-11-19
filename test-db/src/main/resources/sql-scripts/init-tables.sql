INSERT INTO `driver` (`driver_id`, `name`, `dateStartWork`, `salary`) VALUES (1, 'VASIA', '1998-10-01T12:02:01.8472Z', 500),
                                                                              (2, 'VOVA', '2010-10-11T08:30:30.1234Z', 850),
                                                                              (3, 'VITALIY', '2005-04-28T14:44:50.5327Z', 650);
INSERT INTO `car` (`car_id`, `model`, `driver_id`) VALUES (1, 'GAZ', 1),
                                                          (2, 'ZIL', 3),
                                                          (3, 'LADA', 3),
                                                          (4, 'GIGA', 1),
                                                          (5, 'URAL', 3);
