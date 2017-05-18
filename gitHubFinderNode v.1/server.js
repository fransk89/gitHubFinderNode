var prompt = require('prompt');
var request = require('request');

var url = 'http://localhost:8080/gitHubFinder/getProgrammingLanguages.do';
	
// Prompt options
var inputOptions = {
    properties: {
      username: {
		description: 'Git Hub Username?',
        required: true
      },
    }
 };
 
 var quitOptions = {
	properties: {
		finishvalue: {
			description: 'Do you want to continue? (y|n)',
			pattern: 'y|n',
			message: 'You must select Y/N',
			required: true
		}
	}
};

prompt.start();
getProgrammingLanguages();

// Get User´s favourite programming languages
function getProgrammingLanguages() {
	
	
	// Get the username
	prompt.get(inputOptions, function (err, result) {
    
	// Log the results.
    console.log('Getting Git Hub Programming Languages for the User ' + result.username + '...');
	
	var username = result.username;
	
	request.post({url: url, 
		body: username}, 
		function optionalCallback(err, httpResponse, body) {
			if (err) {
				return console.error('Unexpected Error:', err);
			} 
			if (body != null && body.length > 0) {
				console.log('User Programming languages: ', body);
			} else {
				console.log('User repository does not exist'); 
			}
			
			// Get the exit options
			prompt.get(quitOptions, function (err, result) {
				
				if (result.finishvalue == 'y') {
					getProgrammingLanguages();
				} else {
					prompt.stop();
				}
			});
		}
	);
});








}

 







