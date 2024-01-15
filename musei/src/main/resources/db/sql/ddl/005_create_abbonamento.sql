CREATE TABLE IF NOT EXISTS Abbonamento (
	Cod_Ab Integer primary key auto_increment,
	Tipo Enum('Base','Avanzato','Premium') not null,
	Descrizione varchar(200) not null,
    Prezzo float not null
)