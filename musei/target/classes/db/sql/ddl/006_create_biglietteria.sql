CREATE TABLE IF NOT EXISTS Biglietteria (
	Cod_B Integer primary key auto_increment,
	Ora_Ap time not null,
	Ora_Ch time not null,
	Mod_Pag Enum('Contante','Elettronico','Misto') not null,
	Cod_E_M integer,
	foreign key(Cod_E_M) references Museo(Cod_M) ON DELETE CASCADE
)