/*TRIGGERS*/

drop trigger if exists triggerBeforeInsertTUsuario;
drop trigger if exists triggerBeforeInsertTUsuarioAmigo;

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