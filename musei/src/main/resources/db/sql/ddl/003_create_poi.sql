CREATE TABLE IF NOT EXISTS Poi (
	Cod_Poi Integer primary key auto_increment,
	Descrizione varchar(200) not null,
	Cod_E_M integer not null,
	foreign key(Cod_E_M) references Museo(Cod_M) ON DELETE CASCADE
)