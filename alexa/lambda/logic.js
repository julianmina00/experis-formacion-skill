module.exports = {
    httpPost(data){
        return new Promise(((resolve, reject) => {
            const https = require('https')

            const options = {
              hostname: 'experis-formacion.herokuapp.com',
              port: 443,
              path: '/api/usuarios',
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
                'Content-Length': Buffer.byteLength(data),
                'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU4MzcwODkzNn0.4CO1L2fha0jW-D3oGQBI2KzZo1BgXtcRiIPZIwTudJkl01Rj6DwYn_2WrRiP1R6BJJ8AQMjNKvCmDM6dPmHVgw'
              }
            }
            
            const request = https.request(options, (response) => {
              response.setEncoding('utf8');
              let returnData = '';
        
              response.on('data', (chunk) => {
                returnData += chunk;
              });
        
              response.on('end', () => {
                resolve(JSON.parse(returnData));
              });
        
              response.on('error', (error) => {
                reject(error);
              });
            });
            request.write(data);
            request.end();
        }));
        
    }
}
