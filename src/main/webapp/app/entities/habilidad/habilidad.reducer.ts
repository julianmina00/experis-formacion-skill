import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHabilidad, defaultValue } from 'app/shared/model/habilidad.model';

export const ACTION_TYPES = {
  FETCH_HABILIDAD_LIST: 'habilidad/FETCH_HABILIDAD_LIST',
  FETCH_HABILIDAD: 'habilidad/FETCH_HABILIDAD',
  CREATE_HABILIDAD: 'habilidad/CREATE_HABILIDAD',
  UPDATE_HABILIDAD: 'habilidad/UPDATE_HABILIDAD',
  DELETE_HABILIDAD: 'habilidad/DELETE_HABILIDAD',
  RESET: 'habilidad/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHabilidad>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type HabilidadState = Readonly<typeof initialState>;

// Reducer

export default (state: HabilidadState = initialState, action): HabilidadState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HABILIDAD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HABILIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HABILIDAD):
    case REQUEST(ACTION_TYPES.UPDATE_HABILIDAD):
    case REQUEST(ACTION_TYPES.DELETE_HABILIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HABILIDAD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HABILIDAD):
    case FAILURE(ACTION_TYPES.CREATE_HABILIDAD):
    case FAILURE(ACTION_TYPES.UPDATE_HABILIDAD):
    case FAILURE(ACTION_TYPES.DELETE_HABILIDAD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HABILIDAD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_HABILIDAD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HABILIDAD):
    case SUCCESS(ACTION_TYPES.UPDATE_HABILIDAD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HABILIDAD):
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

const apiUrl = 'api/habilidads';

// Actions

export const getEntities: ICrudGetAllAction<IHabilidad> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_HABILIDAD_LIST,
    payload: axios.get<IHabilidad>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IHabilidad> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HABILIDAD,
    payload: axios.get<IHabilidad>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHabilidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HABILIDAD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHabilidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HABILIDAD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHabilidad> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HABILIDAD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
