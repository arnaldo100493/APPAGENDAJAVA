drop trigger if exists triggerBeforeInsertTUsuario;
drop trigger if exists triggerBeforeInsertTUsuarioAmigo;

drop database if exists dbappagendajava;

create database dbappagendajava;
use dbappagendajava;

create table tusuario(
  codigoUsuario char(15) not null,
  nombre varchar(30) not null,
  apellidoPaterno varchar(20) not null,
  apellidoMaterno varchar(20) not null,
  correoElectronico varchar(30) not null,
  contrasenia varchar(700) not null,
  fechaNacimiento date not null,
  sexo bool not null,
  telefono varchar(20),
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  primary key(codigoUsuario)
);

create table tusuarioamigo(
  codigoUsuarioAmigo char(15) not null,
  codigoUsuario char(15) not null,
  nombre varchar(30) not null,
  apellidoPaterno varchar(20) not null,
  apellidoMaterno varchar(20) not null,
  correoElectronico varchar(30) not null,
  contrasenia varchar(700) not null,
  fechaNacimiento date not null,
  sexo bool not null,
  telefono varchar(20),
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  foreign key(codigoUsuario) references tusuario(codigoUsuario)
  on update cascade on delete cascade,
  primary key(codigoUsuarioAmigo)
); 

create table tusuarioamigotelefono(
  codigoUsuarioAmigoTelefono char(15) not null,
  codigoUsuarioAmigo char(15) not null,
  descripcion text not null,
  telefono varchar(20) not null,
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  foreign key(codigoUsuarioAmigo) references tusuarioamigo(codigoUsuarioAmigo)
  on update cascade on delete cascade,
  primary key(codigoUsuarioAmigoTelefono)
);

create table tunidadmedida(
  codigoUnidadMedida char(15) not null,
  nombre varchar(30) not null,
  descripcion text not null,
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  primary key(codigoUnidadMedida)
);

create table tactividad(
  codigoActividad char(15) not null,
  codigoUsuario char(15) not null,
  nombre varchar(700) not null,
  descripcion text not null,
  fechaInicio datetime not null,
  fechaFin datetime not null,
  presupuestoTotal decimal(8, 2) not null,
  estado char(1) not null,
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  foreign key(codigoUsuario) references tusuario(codigoUsuario)
  on update cascade on delete cascade,
  primary key(codigoActividad)
);

create table tactividadpresupuesto(
  codigoActividadPresupuesto char(15) not null,
  codigoActividad char(15) not null,
  codigoUnidadMedida char(15) not null,
  descripcion text not null,
  precioUnitario decimal(8, 2) not null,
  cantidad float not null,
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  foreign key(codigoActividad) references tactividad(codigoActividad)
  on update cascade on delete cascade,
  foreign key(codigoUnidadMedida) references tunidadmedida(codigoUnidadMedida)
  on update cascade on delete cascade,
  primary key(codigoActividadPresupuesto)
);

create table tactividadparticipante(
  codigoActividadParticipante char(15) not null,
  codigoActividad char(15) not null,
  codigoUsuarioAmigo char(15) not null,
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  foreign key(codigoActividad) references tactividad(codigoActividad)
  on update cascade on delete cascade,
  foreign key(codigoUsuarioAmigo) references tusuarioamigo(codigoUsuarioAmigo)
  on update cascade on delete cascade,
  primary key(codigoActividadParticipante)
);

create table tactividadcomentario(
  codigoActividadComentario char(15) not null,
  codigoActividad char(15) not null,
  codigoUsuarioAmigo char(15) not null,
  comentario text not null,
  fechaRegistro timestamp not null default current_timestamp,
  fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
  foreign key(codigoActividad) references tactividad(codigoActividad)
  on update cascade on delete cascade,
  foreign key(codigoUsuarioAmigo) references tusuarioamigo(codigoUsuarioAmigo)
  on update cascade on delete cascade,
  primary key(codigoActividadComentario)
);

/*TRIGGERS*/

delimiter $$
create trigger triggerBeforeInsertTUsuario before insert on tusuario for each row
begin
set @ultimoCodigo=(select max(codigoUsuario) from tusuario);
if @ultimoCodigo is null then
   set @ultimoCodigo="USUARIOX0000000";
end if;
set @parteTexto=mid(@ultimoCodigo, 1, 8);
set @parteNumerica=mid(@ultimoCodigo, 9, 7)+1;
set @longitudNumero=(select length(@parteNumerica));
set @codigoNumerico=concat(repeat('0', 7-@longitudNumero), @parteNumerica);
set @codigo=concat(@parteTexto, @codigoNumerico);
set new.codigoUsuario=(select @codigo);
end
$$

delimiter $$
create trigger triggerBeforeInsertTUsuarioAmigo before insert on tusuarioamigo for each row
begin
set @ultimoCodigo=(select max(codigoUsuarioAmigo) from TUsuarioAmigo);
if @ultimoCodigo is null then
	set @ultimoCodigo="USUARIOA0000000";
end if;
set @parteTexto=mid(@ultimoCodigo, 1, 8);
set @parteNumerica=mid(@ultimoCodigo, 9, 7)+1;
set @longitudNumero=(select length(@parteNumerica));
set @codigoNumerico=concat(repeat('0', 7-@longitudNumero), @parteNumerica);
set @codigo=concat(@parteTexto, @codigoNumerico);
set new.codigoUsuarioAmigo=(select @codigo);
end
$$

insert into tusuario(codigoUsuario, nombre, apellidoPaterno, apellidoMaterno, correoElectronico, contrasenia, fechaNacimiento, sexo, telefono) values('1', 'Arnaldo Andres', 'Barrios', 'Mena', 'cyberarnaldo04@hotmail.com', '100493', '1993-04-10', true, '3108293114');

$$

select * from tusuario;
