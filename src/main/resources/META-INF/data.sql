
INSERT INTO company_entity(id, companyName, country, city)
VALUES
(1, 'Microsoft', 'US', '???'),
(2, 'Nintendo', 'Japan', 'GloriousNippon'),
(3, 'BasementDudes', 'Antarktis', 'North Pole'),
(4, 'Danielcraft', 'MotherlandAfrica', 'Southern Lamp');

INSERT INTO game_entity(id, title, company_id, verified)
VALUES
(1, 'Halo', 1, true),
(2, 'Hallå', 2, false),
(3, 'Hallon', 2, false),
(4, 'Haloeller', 2, false),
(5, 'Halloes', 2, true),
(6, 'Hajro II', 1, true),
(7, 'Mario cart', 4, true),
(8, 'Mario party', 4, true),
(9, 'Final Fantasy 1337', 3, false);

INSERT INTO platform_entity(id, name, releaseYear, company_id)
VALUES
(1, 'XBox', 2001, 1),
(2, 'PC', 2002, 4),
(3, 'Playstation', 1998, 2),
(4, 'Nintendo', 1984, 3);

INSERT INTO rating_entity(id, user_id, game_id, rating)
VALUES
(1, 1, 1, 5),
(2, 2, 1, 3),
(3, 3, 1, 0),
(4, 4, 1, 3);

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SEARCH_FIVE_GAMES`(in query varchar(255))
  begin
    SELECT *
    FROM game_entity
    WHERE title = query+'%'
    AND verified = true
    LIMIT 5;
  end
$$
DELIMITER ;