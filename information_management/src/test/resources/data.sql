insert into person(`id`, `name`) values (1, 'martin');
insert into person(`id`, `name`) values (2, 'david');
insert into person(`id`, `name`) values (3, 'dennis');
insert into person(`id`, `name`) values (4, 'sophia');
insert into person(`id`, `name`) values (5, 'benny');

insert into block(`id`, `name`) values (1, 'dennis');
insert into block(`id`, `name`) values (2, 'sophia');

update person set block_id = 1 where id = 3;
update person set block_id = 2 where id = 4;
