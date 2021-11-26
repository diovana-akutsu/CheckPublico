create database check_database;
use check_database;

create table Usuario(
idUsuario int primary key auto_increment,
nome varchar(100) not null,
email varchar(100) not null,
senha varchar(45) not null
);

create table Obra(
idObra int primary key auto_increment,
nome varchar(45),
categoria varchar (20),
cep varchar (10),
rua varchar (50),
numero varchar (6),
bairro varchar(45),
cidade varchar(45),
estado varchar(45),
latitude DECIMAL(7, 4),
longitude DECIMAL(7, 4)
);

create table Publicacao(
idPublicacao int primary key auto_increment,
descricao varchar(500) not null,
inicioObra datetime,
finalObra datetime,
orcamento decimal(15,2),
imagemObra varchar(250),
fk_Usuario int,
fk_Obra int,
foreign key(fk_Usuario) references Usuario(idUsuario),
foreign key(fk_Obra) references Obra(idObra)
);

create table ComentariosObras(
idComentario int primary key auto_increment,
fk_Usuario int,
fk_Publicacao int,
comentario varchar(200),
foreign key(fk_Usuario) references Usuario(idUsuario),
foreign key(fk_Publicacao) references Publicacao(idPublicacao));

desc publicacao;