create procedure sp_crear_examen_dettalleExamen
	@idTipoPrueba int,
	@fechaExamen date,
	@idProfesor int,
	@idAlumno int,
	@observaciones nvarchar,
	@idCategoria int
	--LOS PARAMETROS PARA INSERT EN DETALLE DE EXAMEN
	
as
begin transaction
	insert into examenes (idTipoExamen, fechaExamen, encargadoExamen, idAlumno, observaciones, idCategoria)
                    values (@idTipoPrueba, @fechaExamen, @idProfesor, @idAlumno, @observaciones, @idCategoria);
	
	declare @idUltExamen int
	select @idUltExamen = (select top 1 idExamen
							from examenes
							order by idExamen desc);

