CREATE TABLE IF NOT EXISTS Cliente_Abbonamento (
	Cod_CA integer primary key auto_increment,
	Cod_E_Cli integer ,
	Cod_E_A integer ,
	foreign key(Cod_E_Cli) references Cliente(Cod_Cli) ON DELETE CASCADE,
	foreign key(Cod_E_A) references Abbonamento(Cod_Ab) ON DELETE CASCADE
)