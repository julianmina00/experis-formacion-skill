// This sample demonstrates handling intents from an Alexa skill using the Alexa Skills Kit SDK (v2).
// Please visit https://alexa.design/cookbook for additional examples on implementing slots, dialog management,
// session persistence, api calls, and more.
const Alexa = require('ask-sdk-core');
const interceptors = require('./interceptors');
const persistence = require('./persistence');
const logic = require('./logic');

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
