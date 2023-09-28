-- Crear tabla de usuarios
CREATE TABLE `users` (
	`username` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`enabled` tinyint(1) NOT NULL,
	PRIMARY KEY (`username`)
) ENGINE=InnoDB;

-- Crear tabla de roles
CREATE TABLE `authorities` (
	`username` varchar(50) NOT NULL,
	`authority` varchar(50) NOT NULL,
	UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
	CONSTRAINT `authorities_ibfk_1`
	FOREIGN KEY (`username`)
	REFERENCES `users` (`username`)
) ENGINE=InnoDB;

-- Insertamos nuestros usuarios
INSERT INTO `users` VALUES ('nicolas','{noop}secreto',1);
INSERT INTO `users` VALUES ('miguel','{noop}secreto',1);

-- Insertamos (asignamos roles) a nuestros usuarios.
INSERT INTO `authorities` VALUES ('nicolas','SUPERVISOR');
INSERT INTO `authorities` VALUES ('miguel','ADMINISTRADOR');

CREATE TABLE Usuarios (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45),
    email VARCHAR(100),
    username VARCHAR(45),
    password VARCHAR(100),
    estatus INT(11),
    fechaRegistro DATE,
    PRIMARY KEY (id)
);

CREATE TABLE Perfiles (
    id INT(11) NOT NULL AUTO_INCREMENT,
    perfil VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE UsuarioPerfil (
    idUsuario INT(11),
    idPerfil INT(11),
    PRIMARY KEY (idUsuario, idPerfil),
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (idPerfil) REFERENCES Perfiles(id) ON DELETE CASCADE
);

-- Insertamos los perfiles disponibles
INSERT INTO `Perfiles` VALUES (1, 'SUPERVISOR');
INSERT INTO `Perfiles` VALUES (2, 'ADMINISTRADOR');
INSERT INTO `Perfiles` VALUES (3, 'USUARIO');
-- Insertamos 2 usuarios
INSERT INTO `Usuarios` VALUES (2,'user','user@user.cl','user','{noop}user123',1,'2019-06-10');
INSERT INTO `Usuarios` VALUES (3,'admin','admin@admin.cl','admin','{noop}admin123',1,'2019-06-10');

-- Insertamos los roles para los usuarios
INSERT INTO `UsuarioPerfil` VALUES (2, 1); -- PERFIL SUPERVISOR
INSERT INTO `UsuarioPerfil` VALUES (3, 2); -- PERFIL ADMINISTRADOR