import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUsuario, defaultValue } from 'app/shared/model/usuario.model';

export const ACTION_TYPES = {
  FETCH_USUARIO_LIST: 'usuario/FETCH_USUARIO_LIST',
  FETCH_USUARIO: 'usuario/FETCH_USUARIO',
  CREATE_USUARIO: 'usuario/CREATE_USUARIO',
  UPDATE_USUARIO: 'usuario/UPDATE_USUARIO',
  DELETE_USUARIO: 'usuario/DELETE_USUARIO',
  RESET: 'usuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUsuario>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: UsuarioState = initialState, action): UsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_USUARIO):
    case REQUEST(ACTION_TYPES.DELETE_USUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USUARIO):
    case FAILURE(ACTION_TYPES.CREATE_USUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_USUARIO):
    case FAILURE(ACTION_TYPES.DELETE_USUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_USUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_USUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USUARIO):
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

const apiUrl = 'api/usuarios';

// Actions

export const getEntities: ICrudGetAllAction<IUsuario> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USUARIO_LIST,
    payload: axios.get<IUsuario>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USUARIO,
    payload: axios.get<IUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
