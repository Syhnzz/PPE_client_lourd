drop database if exists mairieV; 
create database mairieV; 
use mairieV; 

create table user (
	iduser int(3) not null auto_increment, 
	nom varchar(50), 
	prenom varchar(50), 
	email varchar(50), 
	mdp  varchar (255), 
	role enum("admin", "user"), 
	primary key (iduser)
);

create table utilisateur(
	id_user int(100) NOT NULL AUTO_INCREMENT,
	nom varchar(15) not null,
	prenom varchar(15),
	email varchar(50) UNIQUE,
	age int(3),
	password_hash varchar(255),
	PRIMARY KEY(id_user)
);

create table enfant(
	id_enfant int(100) NOT NULL AUTO_INCREMENT,
	nom varchar(15) not null,
	prenom varchar(15),
	age int(3),
	PRIMARY KEY(id_enfant)
);

create table centreLoisir(
	idcl int(100) not null auto_increment,
	ville varchar(100),
	id_enfant int(100) not null, 
	primary key (idcl), 
	foreign key (id_enfant) references enfant(id_enfant)
);

create table activite (
	id_act int(100) not null auto_increment, 
	nom varchar(100), 
    date_act Date,
    statut enum ("Sportif", "Culturel"),
	primary key (id_act)
);


create table rdvMairie (
	id_rdv int(100) not null auto_increment, 
	motif varchar(100), 
	rdv_date Date, 
	service enum ("affaire oublié","Etat Civil", "Habitation"),
	id_user int(3) not null, 
	primary key (id_rdv), 
	foreign key (id_user) references utilisateur(id_user)
); 

insert into user values 
(null, "Olivier", "Paul", "a@gmail.com", "123", "admin"), 
(null, "Marie", "Lucie", "b@gmail.com", "456", "user"); 

insert into utilisateur values 
(null, "exemple", "exemple", "exemple@gmail.com", 23 , "123");

insert into enfant values 
(null, "exemple", "exemple", 13);











