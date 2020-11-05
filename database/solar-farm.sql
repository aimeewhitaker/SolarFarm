drop database if exists solar_farm;
create database solar_farm;
use solar_farm;

create table panel_type (
	panel_type_id int primary key auto_increment,
    `name` varchar(25)
);

insert into panel_type (`name`) values ('CIGS');
insert into panel_type (`name`) values ('POLYSI');
insert into panel_type (`name`) values ('CDTE');
insert into panel_type (`name`) values ('MULTICH');
insert into panel_type (`name`) values ('SI');
insert into panel_type (`name`) values ('MONOCH');

create table solar_farm (
    panel_id int primary key auto_increment,
    section varchar(50),
    row_num int,
    col_num int,
    year_installed int,
    panel_type_id int,
    is_tracking boolean,
    foreign key (panel_type_id)
		references panel_type(panel_type_id)
);

insert into solar_farm (section, row_num, col_num, year_installed, panel_type_id, is_tracking) values ('The Ridge', 1, 1, 2012, 2, true);
insert into solar_farm (section, row_num, col_num, year_installed, panel_type_id, is_tracking) values ('The Ridge', 5, 8, 2010, 1, false);
insert into solar_farm (section, row_num, col_num, year_installed, panel_type_id, is_tracking) values ('The Hills', 5, 1, 2012, 5, true);
