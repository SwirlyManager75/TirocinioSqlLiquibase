CREATE TABLE IF NOT EXISTS Cliente (
	Cod_Cli Integer primary key auto_increment,
	Nome varchar(50) not null,
	Cognome varchar(50) ,
	Mail varchar(60) ,
    Cellulare varchar(20) ,
	Cod_E_Ci integer,
	foreign key(Cod_E_Ci) references Citta(Cod_Ci) ON DELETE CASCADE
)