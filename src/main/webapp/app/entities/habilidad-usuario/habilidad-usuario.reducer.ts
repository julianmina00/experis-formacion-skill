import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHabilidadUsuario, defaultValue } from 'app/shared/model/habilidad-usuario.model';

export const ACTION_TYPES = {
  FETCH_HABILIDADUSUARIO_LIST: 'habilidadUsuario/FETCH_HABILIDADUSUARIO_LIST',
  FETCH_HABILIDADUSUARIO: 'habilidadUsuario/FETCH_HABILIDADUSUARIO',
  CREATE_HABILIDADUSUARIO: 'habilidadUsuario/CREATE_HABILIDADUSUARIO',
  UPDATE_HABILIDADUSUARIO: 'habilidadUsuario/UPDATE_HABILIDADUSUARIO',
  DELETE_HABILIDADUSUARIO: 'habilidadUsuario/DELETE_HABILIDADUSUARIO',
  RESET: 'habilidadUsuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHabilidadUsuario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HabilidadUsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: HabilidadUsuarioState = initialState, action): HabilidadUsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HABILIDADUSUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HABILIDADUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HABILIDADUSUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_HABILIDADUSUARIO):
    case REQUEST(ACTION_TYPES.DELETE_HABILIDADUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HABILIDADUSUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HABILIDADUSUARIO):
    case FAILURE(ACTION_TYPES.CREATE_HABILIDADUSUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_HABILIDADUSUARIO):
    case FAILURE(ACTION_TYPES.DELETE_HABILIDADUSUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HABILIDADUSUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HABILIDADUSUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HABILIDADUSUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_HABILIDADUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HABILIDADUSUARIO):
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

const apiUrl = 'api/habilidad-usuarios';

// Actions

export const getEntities: ICrudGetAllAction<IHabilidadUsuario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HABILIDADUSUARIO_LIST,
  payload: axios.get<IHabilidadUsuario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHabilidadUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HABILIDADUSUARIO,
    payload: axios.get<IHabilidadUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHabilidadUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HABILIDADUSUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHabilidadUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HABILIDADUSUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHabilidadUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HABILIDADUSUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
