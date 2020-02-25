// This sample demonstrates handling intents from an Alexa skill using the Alexa Skills Kit SDK (v2).
// Please visit https://alexa.design/cookbook for additional examples on implementing slots, dialog management,
// session persistence, api calls, and more.
const Alexa = require('ask-sdk-core');
const interceptors = require('./interceptors');
const persistence = require('./persistence');
const logic = require('./logic');
const constants = require('./constants');

const utils = {
    otrasOpciones(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        let opcionesOutput = '...'+requestAttributes.t('OPCIONES_DISPONIBLES_MSG');
        let opcionesDisponibles = 0;
        if(logic.getSessionAttribute(handlerInput, 'idiomasRegistrados')  !== true){
            opcionesOutput += '...'+requestAttributes.t('OPCION_IDIOMAS_MSG');
            opcionesDisponibles++;
        }
        if(logic.getSessionAttribute(handlerInput, 'habilidadesRegistradas')  !== true){
            opcionesOutput += '...'+requestAttributes.t('OPCION_HABILIDADES_MSG');
            opcionesDisponibles++;
        }
        if(logic.getSessionAttribute(handlerInput, 'interesesRegistrados')  !== true){
            opcionesOutput += '...'+requestAttributes.t('OPCION_INTERESES_MSG');
            opcionesDisponibles++;
        }
        if(opcionesDisponibles === 0){
            return '...'+requestAttributes.t('OPCION_AYUDA_MSG');
        }
        return opcionesOutput;
    },
    
    
    descripcionFormacion(handlerInput){
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const formaciones = logic.getSessionAttribute(handlerInput, 'formaciones');
        let seq = logic.getSessionAttribute(handlerInput, 'formacionesNextIndex');
        if(seq === undefined || seq === ''){
            seq = 0;
        }
        console.log('Index: '+seq+' -  formacionesNextIndex: '+logic.getSessionAttribute(handlerInput, 'formacionesNextIndex'));
        console.log('procesando descripcionFormacion para index: '+seq+' y formaciones: '+JSON.stringify(formaciones));
        if(formaciones === undefined){
            return requestAttributes.t('NO_HAY_FORMACIONES_MSG');
        }
        console.log('formaciones['+seq+']: '+JSON.stringify(formaciones[seq]));
        if(formaciones[seq] === undefined){
            return requestAttributes.t('NO_HAY_MAS_FORMACIONES_MSG');
        }
        
        
        let description = '';
        if(formaciones[seq].fechaInicio === formaciones[seq].fechaFin){
            description += requestAttributes.t('DIA_FORMACION_MSG', formaciones[seq].fechaInicio);
        }
        else{
            description += requestAttributes.t('RANGO_FORMACION_MSG', formaciones[seq].fechaInicio, formaciones[seq].fechaFin);
        }
        
        if(formaciones[seq].tipoFormacion === 'P' || formaciones[seq].tipoFormacion === 'p'){
            description += requestAttributes.t('FORMACION_DESCRIPCION_MSG', 'plan formativo', formaciones[seq].descripcion);
            const cursos = formaciones[seq].cursos;
            if(cursos !== undefined && cursos.length > 0){
                let descCursos = '';
                let i; 
                for (i = 0; i < cursos.length; i++) {
                  if(i>0 && i===cursos.length-1){
                      descCursos += ' y '
                  }
                  descCursos += cursos[i];
                  if(i===cursos.length-1){
                      descCursos += '. '
                  }
                  else{
                      descCursos += ', '
                  }
                  
                }
                description += requestAttributes.t('CURSOS_PLAN_FORMATIVO_MSG',descCursos);
            }
        }
        else{
            description += requestAttributes.t('FORMACION_DESCRIPCION_MSG', 'curso', formaciones[seq].descripcion);
            const curso = formaciones[seq];
            const modalidadCurso = curso.telematicoPresencial === 't' || curso.telematicoPresencial === 'T' ? 'online' : 'presencial';
            description += requestAttributes.t('CURSO_PRESENCIAL_ONLINE_MSG', modalidadCurso);
            if(curso.hora !== undefined){
                description += requestAttributes.t('CURSO_HORA_INICIO_MSG', curso.hora);
                if(curso.numeroHoras !== undefined){
                    description += requestAttributes.t('CURSO_DURACION_MSG', curso.numeroHoras);    
                }
            }
            if(curso.ubicacion !== undefined){
                description += requestAttributes.t('CURSO_LUGAR_MSG', curso.ubicacion);
            }
        }
        seq++;
        if(seq < formaciones.length){
            const pendientes = formaciones.length - seq;
            description += requestAttributes.t('MAS_FORMACIONES_MSG', pendientes === 1 ? 'queda una formación' : 'quedan '+pendientes+' formaciones ');
        }
        else{
            description += '<break time="2s"/>'+requestAttributes.t('NO_HAY_MAS_FORMACIONES_MSG');
            description += '.<break time="1s"/>'+requestAttributes.t('OPCION_AYUDA_MSG');
        }
        logic.setSessionAttribute(handlerInput, 'formacionesNextIndex', seq);
        return description; 
    }
};

const LaunchRequestHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'LaunchRequest';
    },
    handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const userName = logic.getSessionAttribute(handlerInput, 'userName');
        let speakOutput = requestAttributes.t('BIENVENIDA_MSG');
        if(userName){
            speakOutput = requestAttributes.t('BIENVENIDO_USUARIO_MSG', userName, userName);
            const habilidades = logic.getSessionAttribute(handlerInput, 'habilidadesRegistradas');
            if(habilidades !== true){
                speakOutput = speakOutput + requestAttributes.t('BIENVENIDA_REGISTRO_HABILIDADES');
            }
            else{
                const intereses = logic.getSessionAttribute(handlerInput, 'interesesRegistrados');
                if(intereses !== true){
                    speakOutput = speakOutput + requestAttributes.t('BIENVENIDA_REGISTRO_INTERESES');
                }
                else{
                    const idiomas = logic.getSessionAttribute(handlerInput, 'idiomasRegistrados');
                    if(idiomas !== true){
                        speakOutput = speakOutput + requestAttributes.t('BIENVENIDA_REGISTRO_INTERESES');
                    }
                }
            }
        }
        
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const RegistrarUsuarioIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'RegistrarUsuarioIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const usuario = JSON.stringify({
          nombre: intent.slots.nombre.value,
          apellidos: intent.slots.apellido.value,
          tipoDocumento: intent.slots.tipoDocumento.resolutions.resolutionsPerAuthority[0].values[0].value.name,
          documento: intent.slots.numeroDocumento.value,
          telefono: intent.slots.telefono.value,
          email: 'email@experis.com',
          rol: intent.slots.perfilTecnico.resolutions.resolutionsPerAuthority[0].values[0].value.name,
          managerNombre: intent.slots.manager.value,
          managerEmail: 'manager@experis.com',
          proyecto: intent.slots.proyecto.value,
          compania:intent.slots.compania.value,
          talentMentorNombre: intent.slots.talentMentor.value,
          talentMentorEmail: 'talent-mentor@experis.com',
          ubicacion: intent.slots.ciudad.value
        });
        
        const response = await logic.registrarUsuario(usuario);
        console.log("Usuario creado: "+JSON.stringify(response));
        logic.setSessionAttribute(handlerInput,'userId', response.id);
        logic.setSessionAttribute(handlerInput,'userName', response.nombre);
        const speakOutput = requestAttributes.t('USUARIO_REGISTRADO_MSG', logic.getSessionAttribute(handlerInput, 'userName'))+utils.otrasOpciones(handlerInput);
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const RegistrarIdiomasIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'RegistrarIdiomasIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const idioma = JSON.stringify({
          usuarioId: logic.getSessionAttribute(handlerInput, 'userId'),
          idioma: intent.slots.idioma.value,
          nivelIdiomaId: intent.slots.nivel.resolutions.resolutionsPerAuthority[0].values[0].value.id
        });
        
        const response = await logic.registrarIdioma(idioma);
        console.log("Idioma registrado: "+JSON.stringify(response));
        logic.setSessionAttribute(handlerInput, 'idiomasRegistrados', true);
        const speakOutput = requestAttributes.t('REGISTRAR_OTRO_IDIOMA_MSG')+utils.otrasOpciones(handlerInput);
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const RegistrarHabilidadesIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'RegistrarHabilidadesIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const habilidad = JSON.stringify({
          usuarioId: logic.getSessionAttribute(handlerInput, 'userId'),
          tipoPrincipal: intent.slots.tipoHabilidadPrincipal.value,
          tipoSecundario: intent.slots.tipoHabilidadEspecifica.value,
          habilidad: intent.slots.habilidad.value
        });
        
        let speakOutput = '';
        const response = await logic.registrarHabilidad(habilidad);
        if(response.usuarioId !== logic.getSessionAttribute(handlerInput, 'userId')){
            speakOutput += requestAttributes.t('ERROR_LLAMANDO_API_MSG', response.detail);
            console.log("Error registrando habilidad: "+JSON.stringify(response));
        }
        else{
            speakOutput += requestAttributes.t('HABILIDAD_REGISTRADA_EXITOSAMENTE_MSG');
            logic.setSessionAttribute(handlerInput, 'habilidadesRegistradas', true);
            console.log("Habilidad registrada: "+JSON.stringify(response));
        }
        speakOutput += '<break time="1s"/>'+requestAttributes.t('REGISTRAR_OTRA_HABILIDAD_MSG')+utils.otrasOpciones(handlerInput);
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const RegistrarInteresesIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'RegistrarInteresesIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const interes = JSON.stringify({
          usuarioId: logic.getSessionAttribute(handlerInput, 'userId'),
          tipoPrincipal: intent.slots.tipoInteresPrincipal.value,
          tipoSecundario: intent.slots.tipoInteresEspecifico.value,
          interes: intent.slots.interes.value
        });
        
        let speakOutput = '';
        const response = await logic.registrarInteres(interes);
        if(response.usuarioId !== logic.getSessionAttribute(handlerInput, 'userId')){
            speakOutput += requestAttributes.t('ERROR_LLAMANDO_API_MSG', response.detail);
            console.log("Error registrando interés: "+JSON.stringify(response));
        }
        else{
            speakOutput += requestAttributes.t('INTERES_REGISTRADO_EXITOSAMENTE_MSG');
            logic.setSessionAttribute(handlerInput, 'interesesRegistrados', true);
            console.log("Interés registrado: "+JSON.stringify(response));
        }
        speakOutput += '<break time="1s"/>'+requestAttributes.t('REGISTRAR_OTRO_INTERES_MSG')+utils.otrasOpciones(handlerInput);
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

// Javi: intent de sugerencia de cursos o planes
const SugerenciaCursosPlanesIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'SugerenciaCursosPlanesIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        let numregistroN = 0;
        var numregistroW = logic.getSessionAttribute(handlerInput, 'numregistro');
        if (numregistroW === '') {
            numregistroN = 0
        }
        else {
            numregistroN = parseInt(numregistroW, 10) + 1
        }
        
        const userName = logic.getSessionAttribute(handlerInput, 'userName');
        // /api/formaciones-sugeridas/ idusuario / Curso o Plan / num registro mostrado / num registros por pagina
        const EntradaSugerenciaCursosPlanes = constants.endpoints.SUGERENCIA_CURSOS_PLANES + logic.getSessionAttribute(handlerInput, 'userId') +
              '/' + intent.slots.PlanesCursos.resolutions.resolutionsPerAuthority[0].values[0].value.id + '/' + numregistroN + '/' + '1'
        
       
        
        let speakOutput = '';
        // se debe llamar a la logic del SugerenciaCursosPlanesIntent
        const response = await logic.SugerenciaCursosPlanes(EntradaSugerenciaCursosPlanes);
        
        console.log('respuesta api: '+JSON.stringify(response));
    
        if(typeof response[0].id === "undefined"){
            if(intent.slots.PlanesCursos.resolutions.resolutionsPerAuthority[0].values[0].value.id === 'P'){
                speakOutput += requestAttributes.t('NO_HAY_SUGERENCIA_PLANES', userName);
            }
            else{
                speakOutput += requestAttributes.t('NO_HAY_SUGERENCIA_CURSOS', userName);
            }
            console.log("No se han obtenido sugerencia de cursos o planes: "+JSON.stringify(response));
            logic.setSessionAttribute(handlerInput,'numregistro', '');
            logic.setSessionAttribute(handlerInput,'formacionId', '');
            logic.setSessionAttribute(handlerInput,'tipoFormacion', '');
            logic.setSessionAttribute(handlerInput,'DescrFormacion', '');
        }
        else{
            // QUEDA ver si se necesita además separar dia mes año
             if(intent.slots.PlanesCursos.resolutions.resolutionsPerAuthority[0].values[0].value.id === 'P'){
                speakOutput += requestAttributes.t('SUGERENCIA_PLAN', response[0].descripcion, response[0].fechaInicio, response[0].fechaFin);
            }
            else{
                speakOutput += requestAttributes.t('SUGERENCIA_CURSO', response[0].descripcion, response[0].fechaInicio, response[0].fechaFin);
            }
            //logic.setSessionAttribute(handlerInput, 'interesesRegistrados', true);
            console.log("sugerencia curso/plan obtenida: "+JSON.stringify(response));
        
            // QUEDA toda la logica de apuntate o quieres que busque mas, REVISAR lo de UTILS.OTRASOPCIONES????
        
            //numregistroW = numregistroN.toString();
            //console.log("sugerencia curso/plan obtenida: "+numregistroN);
            //console.log("sugerencia curso/plan obtenida: "+numregistroW);
            //console.log("sugerencia curso/plan obtenida: "+response[0].id);
            //console.log("sugerencia curso/plan obtenida: "+response[0].tipoFormacion);
            //console.log("sugerencia curso/plan obtenida: "+response[0].descripcion);
            logic.setSessionAttribute(handlerInput,'numregistro', numregistroN);
            logic.setSessionAttribute(handlerInput,'formacionId', response[0].id);
            logic.setSessionAttribute(handlerInput,'tipoFormacion', response[0].tipoFormacion);
            logic.setSessionAttribute(handlerInput,'DescrFormacion', response[0].descripcion);
            speakOutput += '<break time="1s"/>'+requestAttributes.t('APUNTARSE_O_SIGUIENTE')+utils.otrasOpciones(handlerInput);
        }
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

//FIN JAVI

const ConsultaFormacionesUsuarioIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'ConsultaFormacionesUsuarioIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const usuarioId = logic.getSessionAttribute(handlerInput, 'userId');
        const fechaInicio = intent.slots.fechaInicio.value;
        let fechaFin = intent.slots.fechaFin.value;
        console.log("Fecha fin: "+fechaFin);
        if(fechaFin === undefined){
            fechaFin = fechaInicio;
        }
        
        let speakOutput = '';
        const response = await logic.consultarFormacionUsuario(usuarioId, fechaInicio, fechaFin);
        console.log('response: --> '+JSON.stringify(response))+' <-- '+response;
        if(response[0] === undefined ){
            speakOutput += requestAttributes.t('NO_HAY_FORMACIONES_MSG');
            logic.setSessionAttribute(handlerInput, 'formaciones', undefined);
            logic.setSessionAttribute(handlerInput, 'formacionesNextIndex', undefined);
        }
        else{
            console.log("Formaciones del usuario: "+JSON.stringify(response));
            logic.setSessionAttribute(handlerInput, 'formaciones', response);
            logic.setSessionAttribute(handlerInput, 'formacionesNextIndex', undefined);
            speakOutput += utils.descripcionFormacion(handlerInput);
        }
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const MasFormacionesIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'MasFormacionesIntent';
    },
    handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const speakOutput = utils.descripcionFormacion(handlerInput);
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

// ApuntarseFormacionIntent
const ApuntarseFormacionIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'ApuntarseFormacionIntent';
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        console.log('processando intent: '+Alexa.getIntentName(handlerInput.requestEnvelope));
        
        let speakOutput = '';
        if(typeof logic.getSessionAttribute(handlerInput, 'formacionId') === "undefined"){
            speakOutput += requestAttributes.t('BUSCA_PRIMERO_FORMACION');
        }
        else {
          const formacion = JSON.stringify({
          usuarioId: logic.getSessionAttribute(handlerInput, 'userId'),
          tipoFormacion: logic.getSessionAttribute(handlerInput, 'tipoFormacion'),
          formacionId: logic.getSessionAttribute(handlerInput, 'formacionId'),
          }); 
          const response = await logic.registrarFormacionUsuario(formacion);
          console.log('respuesta api: '+JSON.stringify(response));
          
          if(response.usuarioId !== logic.getSessionAttribute(handlerInput, 'userId')){
                speakOutput += requestAttributes.t('ERROR_LLAMANDO_API_MSG', response.detail);
                console.log("Error registrando formacion: "+JSON.stringify(response));
            }
            else{
                if(logic.getSessionAttribute(handlerInput, 'tipoFormacion') === 'C'){
                    speakOutput += requestAttributes.t('REGISTRADO_FORMACION_CURSO',logic.getSessionAttribute(handlerInput, 'DescrFormacion'));
                }
                else{
                    speakOutput += requestAttributes.t('REGISTRADO_FORMACION_PLAN',logic.getSessionAttribute(handlerInput, 'DescrFormacion'));
                }
                console.log("formación registrada: "+JSON.stringify(response));
            }
        }
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const HelpIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'AMAZON.HelpIntent';
    },
    handle(handlerInput) {
        const speakOutput = 'You can say hello to me! How can I help?';

        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};
const CancelAndStopIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && (Alexa.getIntentName(handlerInput.requestEnvelope) === 'AMAZON.CancelIntent'
                || Alexa.getIntentName(handlerInput.requestEnvelope) === 'AMAZON.StopIntent');
    },
    handle(handlerInput) {
        const speakOutput = 'Goodbye!';
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .getResponse();
    }
};
const SessionEndedRequestHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'SessionEndedRequest';
    },
    handle(handlerInput) {
        // Any cleanup logic goes here.
        return handlerInput.responseBuilder.getResponse();
    }
};

// The intent reflector is used for interaction model testing and debugging.
// It will simply repeat the intent the user said. You can create custom handlers
// for your intents by defining them above, then also adding them to the request
// handler chain below.
const IntentReflectorHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest';
    },
    handle(handlerInput) {
        const intentName = Alexa.getIntentName(handlerInput.requestEnvelope);
        const speakOutput = 'Has lanzado el intent ${intentName}';

        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

// Generic error handling to capture any syntax or routing errors. If you receive an error
// stating the request handler chain is not found, you have not implemented a handler for
// the intent being invoked or included it in the skill builder below.
const ErrorHandler = {
    canHandle() {
        return true;
    },
    handle(handlerInput, error) {
        console.log(`~~~~ Error handled: ${error.stack}`);
        const speakOutput = `Sorry, I had trouble doing what you asked. Please try again.`;

        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

// The SkillBuilder acts as the entry point for your skill, routing all request and response
// payloads to the handlers above. Make sure any new handlers or interceptors you've
// defined are included below. The order matters - they're processed top to bottom.
exports.handler = Alexa.SkillBuilders.custom()
    .addRequestHandlers(
        LaunchRequestHandler,
        RegistrarUsuarioIntentHandler,
        RegistrarHabilidadesIntentHandler,
        RegistrarInteresesIntentHandler,
        RegistrarIdiomasIntentHandler,
        SugerenciaCursosPlanesIntentHandler,
        ConsultaFormacionesUsuarioIntentHandler,
        MasFormacionesIntentHandler,
        ApuntarseFormacionIntentHandler,
        HelpIntentHandler,
        CancelAndStopIntentHandler,
        SessionEndedRequestHandler,
        IntentReflectorHandler // make sure IntentReflectorHandler is last so it doesn't override your custom intent handlers
    )
    .addErrorHandlers(
        ErrorHandler
    )
    .addRequestInterceptors(
        interceptors.LocalizationRequestInterceptor,
        interceptors.LoggingRequestInterceptor,
        interceptors.LoadAttributesRequestInterceptor
    )
    .addResponseInterceptors(
        interceptors.LoggingResponseInterceptor,
        interceptors.SaveAttributesResponseInterceptor
    )
    .withPersistenceAdapter(persistence.getPersistenceAdapter())
    .lambda();

