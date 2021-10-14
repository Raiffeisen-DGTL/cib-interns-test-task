DROP TABLE IF EXISTS "socks";
DROP TABLE IF EXISTS "colors";
DROP TABLE IF EXISTS "cotton_parts";
DROP SEQUENCE IF EXISTS "composite_seq";
DROP SEQUENCE IF EXISTS "color_seq";
DROP SEQUENCE IF EXISTS "sock_seq";

CREATE TABLE IF NOT EXISTS "socks" (
  "id" SERIAL  PRIMARY KEY,
  "quantity" int NOT NULL,
  "colors_id" int NOT NULL,
  "cotton_parts_id" int NOT NULL
);

CREATE TABLE IF NOT EXISTS "colors" (
  "id" SERIAL  PRIMARY KEY,
  "color" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "cotton_parts" (
  "id" SERIAL  PRIMARY KEY,
  "cotton_part" int NOT NULL
);

CREATE SEQUENCE "sock_seq" START 1;
CREATE SEQUENCE "composite_seq" START 1;
CREATE SEQUENCE "color_seq" START 1;
ALTER TABLE "socks" ADD FOREIGN KEY ("colors_id") REFERENCES "colors" ("id");
ALTER TABLE "socks" ADD FOREIGN KEY ("cotton_parts_id") REFERENCES "cotton_parts" ("id");
ALTER TABLE "socks" ADD CONSTRAINT socks_color_cotton_part  UNIQUE ("id", "colors_id", "cotton_parts_id") ;