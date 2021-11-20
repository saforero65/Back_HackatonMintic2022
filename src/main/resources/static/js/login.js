


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

	var respuesta = await request.json();

	console.log(respuesta)

	if (respuesta != "please check all fields") {

		localStorage.token = respuesta.token;
		localStorage.nick = datos.nick;




		const request = await fetch('/auth', {
			method: 'GET',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'Authorization': localStorage.token
			},
		});
		var respuesta = await request.json();

		console.log(respuesta)
		
		window.location.href = '/dash.html'
		

	} else {
		alert("las credenciales son incorrectas: por favor intente nuevamente")
	}
}