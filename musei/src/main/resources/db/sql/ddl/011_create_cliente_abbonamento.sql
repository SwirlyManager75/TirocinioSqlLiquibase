CREATE TABLE IF NOT EXISTS Cliente_Abbonamento (
	Cod_PA integer primary key auto_increment,
	Cod_E_Cli integer not null,
	Cod_E_A integer not null,
	foreign key(Cod_E_Cli) references Cliente(Cod_Cli) ON DELETE CASCADE,
	foreign key(Cod_E_A) references Abbonamento(Cod_Ab) ON DELETE CASCADE
)