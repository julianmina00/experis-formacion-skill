import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInteresUsuario, defaultValue } from 'app/shared/model/interes-usuario.model';

export const ACTION_TYPES = {
  FETCH_INTERESUSUARIO_LIST: 'interesUsuario/FETCH_INTERESUSUARIO_LIST',
  FETCH_INTERESUSUARIO: 'interesUsuario/FETCH_INTERESUSUARIO',
  CREATE_INTERESUSUARIO: 'interesUsuario/CREATE_INTERESUSUARIO',
  UPDATE_INTERESUSUARIO: 'interesUsuario/UPDATE_INTERESUSUARIO',
  DELETE_INTERESUSUARIO: 'interesUsuario/DELETE_INTERESUSUARIO',
  RESET: 'interesUsuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInteresUsuario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InteresUsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: InteresUsuarioState = initialState, action): InteresUsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INTERESUSUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INTERESUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INTERESUSUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_INTERESUSUARIO):
    case REQUEST(ACTION_TYPES.DELETE_INTERESUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INTERESUSUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INTERESUSUARIO):
    case FAILURE(ACTION_TYPES.CREATE_INTERESUSUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_INTERESUSUARIO):
    case FAILURE(ACTION_TYPES.DELETE_INTERESUSUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERESUSUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERESUSUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INTERESUSUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_INTERESUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INTERESUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/interes-usuarios';

// Actions

export const getEntities: ICrudGetAllAction<IInteresUsuario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INTERESUSUARIO_LIST,
  payload: axios.get<IInteresUsuario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInteresUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INTERESUSUARIO,
    payload: axios.get<IInteresUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInteresUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INTERESUSUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInteresUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INTERESUSUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInteresUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INTERESUSUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
