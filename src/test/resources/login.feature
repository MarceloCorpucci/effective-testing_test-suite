#language:es
Característica: Login
	
	@wip
	Esquema del escenario: Diferentes tipos de usuario se loguean al blog
		Dado el usuario con email "<email>" con pass "<pass>"
		Cuando se loguea al blog
		Entonces debería obtener el resultado "<resultado>"
		
		Ejemplos:
			| email 			    | pass 	 | resultado 													 |
			| user99@gmail.com 		| user99 | Successfully logged in as user99@gmail.com. 					 |
			| not_exists@gmail.com  | xyz 	 | Invalid email or password.	 								 |
		#	| deactivated@gmail.com | xyz	 | This user is not active. Please contact an administrator.	 |
			| user99@gmail.com		| 		 | For pass: This field is required. 	 						 |
			|						| user99 | For email: This field is required.	 						 | 	
		