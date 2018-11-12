INSERT INTO user_entity(id, email, first_name, last_name, password, role, user_name)
VALUES
(1, '1user@user.user', 'Arasto', 'Sahbaei', 'power', 'admin', 'arrebarre1337'),
(2, '2user@user.user', 'Robin', 'Svensson', 'power', 'admin', 'RobinLiquid'),
(3, '3user@user.user', 'Daniel', 'Ivestig', 'power', 'admin', 'Daniel'),
(4, '4user@user.user', 'Gustaf', 'Cyborg', 'power', 'admin', 'HighQualityGurka');

INSERT INTO company_entity(id, company_name, country, city)
VALUES
(1, 'Microsoft', 'US', '???'),
(2, 'Nintendo', 'Japan', 'GloriousNippon');

INSERT INTO game_entity(id, title, company_id)
VALUES
(1, 'Halo', 1),
(2, 'Hallå', 1),
(3, 'Hallon', 1),
(4, 'Haloeller', 1),
(5, 'Halloes', 1),
(6, 'Hajro II', 1),
(7, 'Mario cart', 2);

INSERT INTO platform_entity(id, name, release_year, company_id)
VALUES
(1, 'XBox', 2001, 1);

INSERT INTO rating_entity(id, user_id, game_id, rating)
VALUES
(1, 1, 1, 5);
