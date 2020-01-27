#language: es
Característica: Entradas creadas en modo Draft
	
	Como usuario del Blog
	Quiero crear entradas en modo Draft
	Asi podre postear contenido que solo yo pueda ver
	
	Escenario: Creación de Entrada con éxito
		Dado que me encuentro logueado
		Cuando guardo la entrada "New entry" con texto "this is a simple test" y estado "Draft"
		Y me deslogueo
		Entonces no debería poder ver la entrada en el listado
