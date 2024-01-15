CREATE TABLE IF NOT EXISTS Cliente (
	Cod_Cli Integer primary key auto_increment,
	Nome varchar(50) not null,
	Cognome varchar(50) not null,
	Mail varchar(60) not null,
    Cellulare varchar(20) not null,
	Cod_E_Ci integer not null,
	foreign key(Cod_E_Ci) references Citta(Cod_Ci) ON DELETE CASCADE
)