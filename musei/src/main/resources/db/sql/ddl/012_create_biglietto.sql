CREATE TABLE IF NOT EXISTS Biglietto (
	Cod_Bi integer primary key auto_increment,
	Prezzo float not null,
	Tipo Enum('Gratis','Ridotto','Pieno') not null,
	Data date not null,
	Cod_E_Cli integer ,
	Cod_E_B integer ,
	foreign key(Cod_E_Cli) references Cliente(Cod_Cli) ON DELETE CASCADE,
	foreign key(Cod_E_B) references Biglietteria(Cod_B) ON DELETE CASCADE
)