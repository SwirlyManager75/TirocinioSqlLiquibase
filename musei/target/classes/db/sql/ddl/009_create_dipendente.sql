CREATE TABLE IF NOT EXISTS Dipendente (
	Cod_D Integer primary key auto_increment,
	Nome varchar(50) not null,
	Data_Nascita date not null,
	CF varchar(16) not null,
    Cellulare varchar(20) not null,
	Cod_E_Ci integer not null,
    Cod_E_M integer not null,
	foreign key(Cod_E_Ci) references Citta(Cod_Ci) ON DELETE CASCADE,
    foreign key(Cod_E_M) references Museo(Cod_M) ON DELETE CASCADE
)