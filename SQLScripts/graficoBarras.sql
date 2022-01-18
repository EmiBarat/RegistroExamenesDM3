
select tp.descripcion Prueba, AVG(de.puntajeObtenido) Promedio
from detalleExamenes de
	inner join tiposPruebas tp on tp.idPrueba = de.idPrueba
where puntajeObtenido != 0
group by tp.descripcion