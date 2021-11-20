$(document).ready(function() {
	validador()
	console.log("wiii")
});



async function validador() {

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
		if (respuesta == true){
			
		}else {
			window.location.href = '/login.html'
		}
		

	
}