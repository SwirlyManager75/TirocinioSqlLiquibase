CREATE TABLE IF NOT EXISTS Opera (
	Cod_O integer primary key auto_increment,
	Nome varchar(60) not null,
	Descrizione varchar(300) not null,
	Cod_E_Ar integer not null,
	Cod_E_M integer not null,
	foreign key(Cod_E_Ar) references Artista(Cod_Ar) ON DELETE CASCADE,
	foreign key(Cod_E_M) references Museo(Cod_M) ON DELETE CASCADE
)