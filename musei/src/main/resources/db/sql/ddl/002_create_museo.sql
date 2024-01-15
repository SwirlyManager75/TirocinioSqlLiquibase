CREATE TABLE IF NOT EXISTS Museo (
	Cod_M Integer primary key auto_increment,
	Nome varchar(50) not null,
	Via varchar(50) not null,
	Cod_E_Ci integer not null,
	foreign key(Cod_E_Ci) references Citta(Cod_Ci) ON DELETE CASCADE
)