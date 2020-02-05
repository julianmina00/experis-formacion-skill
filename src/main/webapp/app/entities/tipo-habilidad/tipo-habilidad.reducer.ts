import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITipoHabilidad, defaultValue } from 'app/shared/model/tipo-habilidad.model';

export const ACTION_TYPES = {
  FETCH_TIPOHABILIDAD_LIST: 'tipoHabilidad/FETCH_TIPOHABILIDAD_LIST',
  FETCH_TIPOHABILIDAD: 'tipoHabilidad/FETCH_TIPOHABILIDAD',
  CREATE_TIPOHABILIDAD: 'tipoHabilidad/CREATE_TIPOHABILIDAD',
  UPDATE_TIPOHABILIDAD: 'tipoHabilidad/UPDATE_TIPOHABILIDAD',
  DELETE_TIPOHABILIDAD: 'tipoHabilidad/DELETE_TIPOHABILIDAD',
  RESET: 'tipoHabilidad/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITipoHabilidad>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TipoHabilidadState = Readonly<typeof initialState>;

// Reducer

export default (state: TipoHabilidadState = initialState, action): TipoHabilidadState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TIPOHABILIDAD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TIPOHABILIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TIPOHABILIDAD):
    case REQUEST(ACTION_TYPES.UPDATE_TIPOHABILIDAD):
    case REQUEST(ACTION_TYPES.DELETE_TIPOHABILIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TIPOHABILIDAD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TIPOHABILIDAD):
    case FAILURE(ACTION_TYPES.CREATE_TIPOHABILIDAD):
    case FAILURE(ACTION_TYPES.UPDATE_TIPOHABILIDAD):
    case FAILURE(ACTION_TYPES.DELETE_TIPOHABILIDAD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOHABILIDAD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOHABILIDAD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TIPOHABILIDAD):
    case SUCCESS(ACTION_TYPES.UPDATE_TIPOHABILIDAD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TIPOHABILIDAD):
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

const apiUrl = 'api/tipo-habilidads';

// Actions

export const getEntities: ICrudGetAllAction<ITipoHabilidad> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TIPOHABILIDAD_LIST,
    payload: axios.get<ITipoHabilidad>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITipoHabilidad> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TIPOHABILIDAD,
    payload: axios.get<ITipoHabilidad>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITipoHabilidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TIPOHABILIDAD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITipoHabilidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TIPOHABILIDAD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITipoHabilidad> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TIPOHABILIDAD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
