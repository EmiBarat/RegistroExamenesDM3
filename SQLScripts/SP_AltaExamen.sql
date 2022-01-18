create proc altaExamen 
	--parametros para en alta del examen sin la nota
	@idTipoExamen int = 0,
	@fechaExamen varchar(12) = '',
	@idProfesor int = 0,
	@idAlumno int = 0,
	@observaciones varchar(140) = '',
	@idCategoria int = 0,
	@idGenero int = 0,
	
	--parametros para en alta de los detallesExamen
	--!!!!!!!!!!!!!!!!!!!!!!!!!!
	--@idExamen int = 0, el idExamen no se lo voy a poder pasar desde Java
	--voy a tener que recuperar el que se cree en el insert de examen.
	@ultimoIdExamen int = 0,
	@idPruebaCarrera int =1,
	@idPruebaFlexiones int =2,
	@idPruebaBarras int =3,
	@idPruebaAbdominales int =4,
	@idPruebaCaminata int =5,

	--@fechaPrueba varchar (12) = '',
	@resultadoCarrera float = 0,
	@puntajeObtenidoCarrera int = 0,
	@resultadoFlexiones float = 0,
	@puntajeObtenidoFlexiones int = 0,
	@resultadoBarras float = 0,
	@puntajeObtenidoBarras int = 0,
	@resultadoAbdominales float = 0,
	@puntajeObtenidoAbdominales int = 0,
	@resultadoCaminata float = 0,
	@puntajeObtenidoCaminata int = 0,

	--parametros para en upDate de la nota final
	@notaFinal int = 0
	

	as
	begin
		begin try
			begin transaction
				insert into examenes (idTipoExamen, fechaExamen, encargadoExamen, idAlumno, observaciones, idCategoria)
						values (@idTipoExamen, @fechaExamen, @idProfesor, @idAlumno, @observaciones, @idCategoria);
			
				set @ultimoIdExamen = @@IDENTITY;

				--tengo que traer los puntajes obtenidos segun los resultados, la categ, el genero y la prueba...
				--una consulta por cada prueba

				--para carrera
				set @puntajeObtenidoCarrera = (select puntosObtenidos
						from matrizPuntajes
						where idGenero = @idGenero
						and idPrueba = @idPruebaCarrera
						and idCategoria = @idCategoria
						and @resultadoCarrera between exigenciaMin and exigenciaMax)


				insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)
						values(@ultimoIdExamen, @idPruebaCarrera, @fechaExamen, @resultadoCarrera, @puntajeObtenidoCarrera, @idProfesor, @observaciones)

				--para flexiones
				set @puntajeObtenidoFlexiones= (select puntosObtenidos
						from matrizPuntajes
						where idGenero = @idGenero
						and idPrueba = @idPruebaFlexiones
						and idCategoria = @idCategoria
						and @resultadoFlexiones between exigenciaMin and exigenciaMax)


				insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)
						values(@ultimoIdExamen, @idPruebaFlexiones, @fechaExamen, @resultadoFlexiones, @puntajeObtenidoFlexiones, @idProfesor, @observaciones)

				--para barras
				set @puntajeObtenidoBarras = (select puntosObtenidos
						from matrizPuntajes
						where idGenero = @idGenero
						and idPrueba = @idPruebaBarras
						and idCategoria = @idCategoria
						and @resultadoBarras between exigenciaMin and exigenciaMax)


				insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)
						values(@ultimoIdExamen, @idPruebaBarras, @fechaExamen, @resultadoBarras, @puntajeObtenidoBarras, @idProfesor, @observaciones)

				--para abdominales
				set @puntajeObtenidoAbdominales = (select puntosObtenidos
						from matrizPuntajes
						where idGenero = @idGenero
						and idPrueba = @idPruebaAbdominales
						and idCategoria = @idCategoria
						and @resultadoAbdominales between exigenciaMin and exigenciaMax)


				insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)
						values(@ultimoIdExamen, @idPruebaAbdominales, @fechaExamen, @resultadoAbdominales, @puntajeObtenidoAbdominales, @idProfesor, @observaciones)


				--para caminata
				set @puntajeObtenidoCaminata= (select puntosObtenidos
						from matrizPuntajes
						where idGenero = @idGenero
						and idPrueba = @idPruebaCaminata
						and idCategoria = @idCategoria
						and @resultadoCaminata between exigenciaMin and exigenciaMax)


				insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)
						values(@ultimoIdExamen, @idPruebaCaminata, @fechaExamen, @resultadoCaminata, @puntajeObtenidoCaminata, @idProfesor, @observaciones)

			
				update examenes set notafinal = @notaFinal where idExamen = @ultimoIdExamen

			commit transaction
		end try
		begin catch
			rollback transaction
		end catch
	end