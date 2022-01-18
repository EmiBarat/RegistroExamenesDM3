select tp.descripcion Prueba, AVG(de.puntajeObtenido) Promedio
                         from detalleExamenes de
                             inner join tiposPruebas tp on tp.idPrueba = de.idPrueba
                             inner join examenes e on de.idExamen = e.idExamen
                         where puntajeObtenido != 0
                             and e.idCategoria = 2
							 and e.fechaExamen between '2021-01-01' and '2021-07-20'
							 and e.idTipoExamen = 1
							 and e.notaFinal >= 60
                         group by tp.descripcion
