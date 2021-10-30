alter table if exists socks
    add constraint cotton_part_and_color_unique_idx UNIQUE (cotton_part_id, sock_color_id);

alter table if exists cotton_parts
    add constraint cotton_part_unique_idx UNIQUE (cotton_part);

alter table if exists sock_colors
    add constraint sock_color_unique_idx UNIQUE (color);