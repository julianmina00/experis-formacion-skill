// This sample demonstrates handling intents from an Alexa skill using the Alexa Skills Kit SDK (v2).
// Please visit https://alexa.design/cookbook for additional examples on implementing slots, dialog management,
// session persistence, api calls, and more.
const Alexa = require('ask-sdk-core');
const interceptors = require('./interceptors');
const persistence = require('./persistence');
const logic = require('./logic');

//var response = [];

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
        logic.setSessionAttribute(handlerInput,'previousIntent', 'RegistrarUsuarioIntent');
        const speakOutput = requestAttributes.t('USUARIO_REGISTRADO_MSG', logic.getSessionAttribute(handlerInput, 'userName'))+requestAttributes.t('REGISTRAR_IDIOMAS_MSG');
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const OtrosIdiomasIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && ((Alexa.getIntentName(handlerInput.requestEnvelope) === 'SiIdiomasIntent' && logic.getSessionAttribute(handlerInput, 'previousIntent') === 'RegistrarUsuarioIntent')
                || Alexa.getIntentName(handlerInput.requestEnvelope) === 'OtrosIdiomasIntent')
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        
        const idioma = JSON.stringify({
          usuarioId: logic.getSessionAttribute(handlerInput, 'userId'),
          idioma: intent.slots.idioma.value,
          nivelIdiomaId: intent.slots.nivel.resolutions.resolutionsPerAuthority[0].values[0].value.id
        });
        
        const response = await logic.registrarIdioma(idioma);
        console.log("Idioma registrado: "+JSON.stringify(response));
        logic.setSessionAttribute(handlerInput, 'idiomasRegistrados', true);
        let speakOutput = requestAttributes.t('REGISTRAR_OTRO_IDIOMA_MSG');
        
        logic.setSessionAttribute(handlerInput,'previousIntent', Alexa.getIntentName(handlerInput.requestEnvelope));
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const NoIdiomasIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'NoIdiomasIntent'
            && (logic.getSessionAttribute(handlerInput, 'previousIntent') === 'RegistrarUsuarioIntent' 
                || logic.getSessionAttribute(handlerInput, 'previousIntent') === 'SiIdiomasIntent' 
                || logic.getSessionAttribute(handlerInput, 'previousIntent') === 'OtrosIdiomasIntent')
    },
    handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        logic.setSessionAttribute(handlerInput,'previousIntent', Alexa.getIntentName(handlerInput.requestEnvelope));
        
        const speakOutput = requestAttributes.t('NO_IDIOMAS_MSG');
        let repromptOutput = '...';
        if(logic.getSessionAttribute(handlerInput, 'habilidadesRegistradas')  !== true){
            repromptOutput += requestAttributes.t('REGISTRAR_HABILIDAD_MSG');
        }
        else if(logic.getSessionAttribute(handlerInput, 'interesesRegistrados')  !== true){
            repromptOutput += requestAttributes.t('REGISTRAR_INTERES_MSG');
        }
        else{
            repromptOutput += requestAttributes.t('PEDIR_AYUDA_MSG');
        }
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(repromptOutput)
            .getResponse();
    }
};

const RegistrarHabilidadesIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && ((Alexa.getIntentName(handlerInput.requestEnvelope) === 'SiHabilidadesIntent' && logic.getSessionAttribute(handlerInput, 'previousIntent') === 'NoIdiomasIntent')
                || (Alexa.getIntentName(handlerInput.requestEnvelope) === 'SiHabilidadesIntent' && logic.getSessionAttribute(handlerInput, 'previousIntent') === 'SiHabilidadesIntent')
                || (Alexa.getIntentName(handlerInput.requestEnvelope) === 'SiHabilidadesIntent' && logic.getSessionAttribute(handlerInput, 'previousIntent') === 'RegistrarHabilidadesIntent')
                || Alexa.getIntentName(handlerInput.requestEnvelope) === 'RegistrarHabilidadesIntent')
    },
    async handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        const intent = handlerInput.requestEnvelope.request.intent;
        
        const habilidad = JSON.stringify({
          usuarioId: logic.getSessionAttribute(handlerInput, 'userId'),
          tipoPrincipal: intent.slots.areaGeneral.value,
          tipoSecundario: intent.slots.areaEspecifica.value,
          habilidad: intent.slots.habilidad.value
        });
        
        const response = await logic.registrarHabilidad(habilidad);
        console.log("Habilidad registrada: "+JSON.stringify(response));
        logic.setSessionAttribute(handlerInput, 'habilidadesRegistradas', true);
        let speakOutput = requestAttributes.t('REGISTRAR_OTRA_HABILIDAD_MSG');
        logic.setSessionAttribute(handlerInput,'previousIntent', Alexa.getIntentName(handlerInput.requestEnvelope));
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(speakOutput)
            .getResponse();
    }
};

const NoHabilidadesIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'NoHabilidadesIntent'
            && (logic.getSessionAttribute(handlerInput, 'previousIntent') === 'NoIdiomasIntent' 
                || logic.getSessionAttribute(handlerInput, 'previousIntent') === 'SiHabilidadesIntent'  
                || logic.getSessionAttribute(handlerInput, 'previousIntent') === 'RegistrarHabilidadesIntent')
    },
    handle(handlerInput) {
        const {attributesManager} = handlerInput;
        const requestAttributes = attributesManager.getRequestAttributes();
        const sessionAttributes = attributesManager.getSessionAttributes();
        logic.setSessionAttribute(handlerInput,'previousIntent', Alexa.getIntentName(handlerInput.requestEnvelope));
        
        let speakOutput = requestAttributes.t('NO_MAS_HABILIDADES_MSG');
        let repromptOutput = '...';
        if(logic.getSessionAttribute(handlerInput, 'habilidadesRegistradas')  !== true){
            speakOutput = requestAttributes.t('NINGUNA_HABILIDAD_MSG');
        }
        else if(logic.getSessionAttribute(handlerInput, 'interesesRegistrados')  !== true){
            repromptOutput += requestAttributes.t('REGISTRAR_INTERES_MSG');
        }
        else{
            repromptOutput += requestAttributes.t('PEDIR_AYUDA_MSG');
        }
        return handlerInput.responseBuilder
            .speak(speakOutput)
            .reprompt(repromptOutput)
            .getResponse();
    }
};

const HelloWorldIntentHandler = {
    canHandle(handlerInput) {
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest'
            && Alexa.getIntentName(handlerInput.requestEnvelope) === 'HelloWorldIntent';
    },
    handle(handlerInput) {
        const speakOutput = '¡Qué pasa tío!';
        return handlerInput.responseBuilder
            .speak(speakOutput)
            //.reprompt('add a reprompt if you want to keep the session open for the user to respond')
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
        return Alexa.getRequestType(handlerInput.requestEnvelope) === 'IntentRequest' 
                && (Alexa.getIntentName(handlerInput.requestEnvelope) !== 'NoIdiomasIntent' )
                && (Alexa.getIntentName(handlerInput.requestEnvelope) !== 'SiIdiomasIntent' );
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
        OtrosIdiomasIntentHandler,
        NoIdiomasIntentHandler,
        RegistrarHabilidadesIntentHandler,
        NoHabilidadesIntentHandler,
        HelloWorldIntentHandler,
        HelpIntentHandler,
        CancelAndStopIntentHandler,
        SessionEndedRequestHandler/*,
        IntentReflectorHandler*/ // make sure IntentReflectorHandler is last so it doesn't override your custom intent handlers
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
