CREATE TABLE IF NOT EXISTS Opera (
	Cod_O integer primary key auto_increment,
	Nome varchar(60) not null,
	Descrizione varchar(300) ,
	Cod_E_Ar integer ,
	Cod_E_M integer ,
	foreign key(Cod_E_Ar) references Artista(Cod_Ar) ON DELETE CASCADE,
	foreign key(Cod_E_M) references Museo(Cod_M) ON DELETE CASCADE
)