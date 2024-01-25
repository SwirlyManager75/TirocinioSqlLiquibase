CREATE TABLE IF NOT EXISTS Museo (
	Cod_M Integer primary key auto_increment,
	Nome varchar(50) not null,
	Via varchar(50) ,
	Cod_E_Ci integer ,
	foreign key(Cod_E_Ci) references Citta(Cod_Ci) ON DELETE CASCADE
)