insert into person(`id`, `name`, `age`) values (1, 'martin', 10);
insert into person(`id`, `name`, `age`) values (2, 'david', 10);
insert into person(`id`, `name`, `age`) values (3, 'dennis', 10);
insert into person(`id`, `name`, `age`) values (4, 'sophia', 10);
insert into person(`id`, `name`, `age`) values (5, 'benny', 10);

insert into block(`id`, `name`) values (1, 'dennis');
insert into block(`id`, `name`) values (2, 'sophia');

update person set block_id = 1 where id = 3;
update person set block_id = 2 where id = 4;
