-- CREATE SCHEMA
DROP TABLE IF EXISTS socks;
CREATE TABLE IF NOT EXISTS socks
(
    id          serial4 PRIMARY KEY ,
    color       varchar(19),
    cotton_part int2 CHECK ( cotton_part >= 0 AND cotton_part <= 100 ),
    timestamp   timestamptz DEFAULT now()
);


-- GENERATE AND INSERT DATA
INSERT INTO socks (color, cotton_part)
SELECT *
FROM (WITH RECURSIVE q (i, a, b) AS (
    VALUES (0, (random() * 4)::INT2, (random() * 100)::INT2)
    UNION ALL
    SELECT q.i + 1, (random() * 4)::INT2, (random() * 100)::INT2
    FROM q
    WHERE q.i < 5 * 100
)
      SELECT CASE
                 WHEN q.a = 0 THEN 'white'
                 WHEN q.a = 1 THEN 'blue'
                 WHEN q.a = 2 THEN 'green'
                 WHEN q.a = 3 THEN 'pink'
                 WHEN q.a = 4 THEN 'brown'
                 END AS color,
             q.b     AS cotton
      FROM q) AS query;
