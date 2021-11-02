-- CREATE SCHEMA
DROP TABLE IF EXISTS socks;
CREATE TABLE IF NOT EXISTS socks
(
    color       varchar(25),
    cotton_part int2 CHECK ( cotton_part >= 0 AND cotton_part <= 100 ),
    quantity    int8 CHECK ( quantity > 0 ) DEFAULT (random() * 1000)::int4 + 1,
    PRIMARY KEY (color, cotton_part)
);

-- GENERATE AND INSERT DATA
INSERT INTO socks AS s (color, cotton_part)
SELECT qq.color, qq.cotton
FROM (
         WITH RECURSIVE q (i, col, cotton) AS (
             VALUES (0, (random() * 6)::int4, (random() * 100)::int4)
             UNION ALL
             SELECT q.i + 1                AS iter,
                    (random() * 6)::int4   AS col,
                    (random() * 100)::int4 AS cotton
             FROM q
             WHERE q.i < 6 * 10
         )
         SELECT DISTINCT CASE
                             WHEN q.col = 0 THEN 'white'
                             WHEN q.col = 1 THEN 'blue'
                             WHEN q.col = 2 THEN 'green'
                             WHEN q.col = 3 THEN 'pink'
                             WHEN q.col = 4 THEN 'brown'
                             WHEN q.col = 5 THEN 'cyan'
                             WHEN q.col = 6 THEN 'yellow'
                             END AS color,
                         q.cotton
         FROM q
     ) AS qq
ON CONFLICT ON CONSTRAINT socks_pkey DO UPDATE SET quantity = (random() * 100)::int4;