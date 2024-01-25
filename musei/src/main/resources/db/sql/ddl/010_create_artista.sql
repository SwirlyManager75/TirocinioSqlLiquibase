CREATE TABLE IF NOT EXISTS Artista (
	Cod_Ar Integer primary key auto_increment,
	Nome varchar(50) not null,
	Cognome varchar(50) not null,
	Data_Nascita date not null,
    In_vita bit not null,
	Cod_E_Ci integer ,
	foreign key(Cod_E_Ci) references Citta(Cod_Ci) ON DELETE CASCADE
)