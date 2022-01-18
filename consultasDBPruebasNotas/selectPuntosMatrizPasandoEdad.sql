select puntosObtenidos
	from matrizPuntajes mp
	where tiempoMin <= 13
	and tiempoMax >= 13
	and idGenero = 1
	and idPrueba = 1
	and idCategoria = 1
	--(select idCategoria from categorias where edadMinima <= 21 and 21 <= edadMaxima)