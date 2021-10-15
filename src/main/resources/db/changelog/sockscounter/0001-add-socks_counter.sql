CREATE TABLE IF NOT EXISTS socks_counter
(
    id          uuid NOT NULL,
    quantity    int  NOT NULL,
    category_id uuid  NOT NULL,
    CONSTRAINT pk_socks_counter PRIMARY KEY (id),
    CONSTRAINT fk_socks_counter_category_to_socks_category FOREIGN KEY (category_id) REFERENCES socks_category(id)
);