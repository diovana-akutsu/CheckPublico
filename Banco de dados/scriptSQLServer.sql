create table Usuario(
id_usuario int primary key identity (1,1),
nome varchar(100) not null,
email varchar(100) not null,
senha varchar(45) not null,
ativo int
);

create table Obra(
id_obra int primary key identity (10,10),
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
id_publicacao int primary key identity (1,1),
descricao varchar(500) not null,
inicio_obra datetime,
final_obra datetime,
orcamento decimal(15,2),
imagem_obra varchar(250),
fk_usuario int,
fk_obra int,
foreign key(fk_usuario) references Usuario(id_usuario),
foreign key(fk_obra) references Obra(id_obra)
);

create table Interacao (
id_interacao int primary key identity (1,1),
fk_usuario int,
fk_publicacao int,
tipo varchar (50),
comentario varchar(200),
curtir BIT,
foreign key(fk_usuario) references Usuario(id_usuario),
foreign key(fk_publicacao) references Publicacao(id_publicacao));