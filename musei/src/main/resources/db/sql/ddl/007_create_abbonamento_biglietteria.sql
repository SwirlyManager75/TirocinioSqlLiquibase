CREATE TABLE IF NOT EXISTS Abbonamento_Biglietteria (
	Cod_AB Integer primary key auto_increment,
	Cod_E_A integer not null,
	Cod_E_B integer not null,
	foreign key(Cod_E_A) references Abbonamento(Cod_Ab) ON DELETE CASCADE,
	foreign key(Cod_E_B) references Biglietteria(Cod_B) ON DELETE CASCADE
)