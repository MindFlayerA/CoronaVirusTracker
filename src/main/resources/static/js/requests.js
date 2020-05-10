/*Make API calls and convert the response to JSON.
 * 
 * @Param url : The URL of the API where you need to send requests. 
 * @Param behaviour : true if you want to keep the request asynchronus or synchronus.
 * @Param requestMethod: Specify Request Method which can be 'GET', 'PUT', 'POST', 'DELETE'
 * 
 * */
function makeHttpRequest(url, behaviour, requestMethod){
	var response;
	var xhr = new XMLHttpRequest();
	xhr.open(requestMethod, url, behaviour);
	xhr.send();
	
	xhr.onreadystatechange = processRequest;
	
	function processRequest(e) {
		 if (xhr.readyState == 4 && xhr.status == 200) {
			 var response = JSON.parse(xhr.responseText).split(',');
			 response.splice(0,4);
			 var data = response[0];
			 localStorage.setItem('allRaw', response);
			 localStorage.setItem('series', data);
			 //console.log(data.split(','));
		    }
	}
	return response;
}
