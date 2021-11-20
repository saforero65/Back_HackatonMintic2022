async function iniciarSesion() {
	
	let datos = {};
	datos.nick = document.getElementById('username').value;
	datos.password = document.getElementById('password').value;
	const request = await fetch('/api/usuario/login', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		
		body: JSON.stringify(datos)
	});
	
	
	var respuesta = await request.text();
	
	console.log(respuesta)
	
	

	if (respuesta != "please check all fields") {
		
		localStorage.token = respuesta;
		localStorage.email = datos.email;
		
		const request = await fetch('/dashboard.html', {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': localStorage.token
		},
		
		body: JSON.stringify(datos)
		
		
		});
	}else {
		alert("las credenciales son incorrectas: por favor intente nuevamente")
	}
}