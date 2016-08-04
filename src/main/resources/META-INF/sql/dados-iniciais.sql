
insert into Cidade (id, estado, nomeCidade) values (1,'DF', 'Brasília');

insert into Congregacao (id, email, endereco,nomeCongregacao, telefone, cidade_id) values (1,'josecarlostag@gmail.com','SGAS 915 Lote 72 A','915 Sul', '(61)3346-2425', 1 );

insert into Grupo (id, nome, descricao) values (1,'Administradores', 'Administradores');

insert into Usuario (id, email, login, nomeUsuario, perfil, rg, senha, telefone, congregacao_id) values (1, 'josecarlostag@gmail.com','Administrador','Administrador', 'ADMIN','000','123456','(61)99646-3614', 1  );

insert into usuario_grupo (usuario_id, grupo_id) values (1,1);







 