const constants = require('./constants');

module.exports = {
    
    setSessionAttribute(handlerInput, attribute, value){
        const {attributesManager} = handlerInput;
        const sessionAttributes = attributesManager.getSessionAttributes();
        sessionAttributes[attribute] = value;
        console.log('...The session attribute ['+attribute+'] was set to: '+value);
    },
    
    getSessionAttribute(handlerInput, attribute){
        const {attributesManager} = handlerInput;
        const sessionAttributes = attributesManager.getSessionAttributes();
        return sessionAttributes[attribute] ? sessionAttributes[attribute] : '';
    },
    
    registrarUsuario(data){
        console.log("....Registrando usuario: "+data);
        return this.httpPost(data, constants.endpoints.REGISTRO_USUARIOS, 'POST');
    },
    
    registrarIdioma(data){
        console.log("....Registrando idioma: "+data);
        return this.httpPost(data, constants.endpoints.REGISTRO_IDIOMAS, 'POST');
    },
    
    registrarHabilidad(data){
        console.log("....Registrando habilidad: "+data);
        return this.httpPost(data, constants.endpoints.REGISTRO_HABILIDAD, 'POST');
    },
    
    registrarInteres(data){
        console.log("....Registrando interés: "+data);
        return this.httpPost(data, constants.endpoints.REGISTRO_INTERES, 'POST');
    },

    registrarFormacionUsuario(data){
        console.log("....Registrando formacion: "+data);
        return this.httpPost(data, constants.endpoints.REGISTRO_FORMACION_USUARIO, 'POST');
    },
    
    
    consultarFormacionUsuario(usuarioId, fechaInicio, fechaFin){
        console.log("....Consultando formaciones: ["+usuarioId+", "+fechaInicio+", "+fechaFin+"]");
        const url = constants.endpoints.CONSULTA_FORMACION_USUARIO+'/'+usuarioId+'/'+fechaInicio+'/'+fechaFin;
        console.log("....URL: "+url);
        const response = this.httpPost('', url, 'GET');
        console.log("...Formaciones Usuario: "+JSON.stringify(response));
        return response;
    },
    
    
    sessionUsuario(nombreUsuario, numeroIdentificacion){
        console.log("....Recuperando la session del usuario: ["+nombreUsuario+"-"+numeroIdentificacion+"]");
        const url = constants.endpoints.SESSION_USUARIO;
        const headers = {
                  'nombreUsuario': nombreUsuario,
                  'numeroIdentificacion': numeroIdentificacion,
                  'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU4NTM4NjIxMH0.6HxIV2O4cI9K51phtRO8PUIijQy89EO41b-nqHRtCsgcVMhWpVMqyFHo71k0n88NbqYiR8OyaVWWHa0vw8CaVw'
                };
        const response = this.httpPost('', url, 'GET', headers);
        console.log("...Session de usuario: "+JSON.stringify(response));
        return response;
    },
    
    

// JAVI: SugerenciaCursosPlanesIntent
    SugerenciaCursosPlanes(EntradaSugerenciaCursosPlanes){
        console.log("....Sugerencia Cursos Planes: "+EntradaSugerenciaCursosPlanes);
        return this.httpPost('', EntradaSugerenciaCursosPlanes, 'GET');
    },
// FIN JAVI     

    httpPost(data, endpoint, httpMethod, headers){
        return new Promise(((resolve, reject) => {
            if(headers === undefined){
                headers = {
                  'Content-Type': 'application/json',
                  'Content-Length': Buffer.byteLength(data),
                  'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU4NTM4NjIxMH0.6HxIV2O4cI9K51phtRO8PUIijQy89EO41b-nqHRtCsgcVMhWpVMqyFHo71k0n88NbqYiR8OyaVWWHa0vw8CaVw'
                };
            }
            
            
            const https = require('https')
            const options = {
              hostname: 'experis-formacion.herokuapp.com',
              port: 443,
              path: endpoint,
              method: httpMethod,
              headers: headers
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
