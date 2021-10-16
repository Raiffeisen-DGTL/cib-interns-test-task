alter table socks
add constraint socks_color_id_cotton_part_uk unique (color_id, cotton_part);