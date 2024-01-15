CREATE TABLE IF NOT EXISTS Audio (
	Cod_Au Integer primary key auto_increment,
	URL varchar(200),
	Cod_E_Poi integer not null,
	foreign key(Cod_E_Poi) references Poi(Cod_Poi)
)