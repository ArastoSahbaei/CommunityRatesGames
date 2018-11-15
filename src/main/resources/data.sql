INSERT INTO user_entity(id, email, password, role, user_name)
VALUES
(1, '1user@user.user', 'power', 'admin', 'Harastio'),
(2, '2user@user.user', 'power', 'admin', 'RobinLiquid'),
(3, '3user@user.user', 'power', 'admin', 'Daniel'),
(4, '4user@user.user', 'power', 'admin', 'BostonGurka'),
(5, 'that@dude.user', 'whut', 'user', 'Whatdude');

INSERT INTO company_entity(id, company_name, country, city)
VALUES
(1, 'Microsoft', 'US', '???'),
(2, 'Nintendo', 'Japan', 'GloriousNippon'),
(3, 'BasementDudes', 'Antarktis', 'North Pole');

INSERT INTO game_entity(id, title, company_id)
VALUES
(1, 'Halo', 1),
(2, 'Hallå', 2),
(3, 'Hallon', 2),
(4, 'Haloeller', 2),
(5, 'Halloes', 2),
(6, 'Hajro II', 1),
(7, 'Mario cart', 4);
(8, 'Mario party', 4)
(9, 'Final Fantasy 1337', 3)

INSERT INTO platform_entity(id, name, release_year, company_id)
VALUES
(1, 'XBox', 2001, 1),
(2, 'PC', NULL, NULL),
(3, 'Playstation', 1998, 2),
(4, 'Nintendo', 1984, 3);

INSERT INTO rating_entity(id, user_id, game_id, rating)
VALUES
(1, 1, 1, 5),
(2, 2, 1, 3),
(3, 3, 1, 0),
(4, 4, 1, 3);
