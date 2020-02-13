import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRelacionTipoHabilidad, defaultValue } from 'app/shared/model/relacion-tipo-habilidad.model';

export const ACTION_TYPES = {
  FETCH_RELACIONTIPOHABILIDAD_LIST: 'relacionTipoHabilidad/FETCH_RELACIONTIPOHABILIDAD_LIST',
  FETCH_RELACIONTIPOHABILIDAD: 'relacionTipoHabilidad/FETCH_RELACIONTIPOHABILIDAD',
  CREATE_RELACIONTIPOHABILIDAD: 'relacionTipoHabilidad/CREATE_RELACIONTIPOHABILIDAD',
  UPDATE_RELACIONTIPOHABILIDAD: 'relacionTipoHabilidad/UPDATE_RELACIONTIPOHABILIDAD',
  DELETE_RELACIONTIPOHABILIDAD: 'relacionTipoHabilidad/DELETE_RELACIONTIPOHABILIDAD',
  RESET: 'relacionTipoHabilidad/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRelacionTipoHabilidad>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type RelacionTipoHabilidadState = Readonly<typeof initialState>;

// Reducer

export default (state: RelacionTipoHabilidadState = initialState, action): RelacionTipoHabilidadState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RELACIONTIPOHABILIDAD):
    case REQUEST(ACTION_TYPES.UPDATE_RELACIONTIPOHABILIDAD):
    case REQUEST(ACTION_TYPES.DELETE_RELACIONTIPOHABILIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD):
    case FAILURE(ACTION_TYPES.CREATE_RELACIONTIPOHABILIDAD):
    case FAILURE(ACTION_TYPES.UPDATE_RELACIONTIPOHABILIDAD):
    case FAILURE(ACTION_TYPES.DELETE_RELACIONTIPOHABILIDAD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RELACIONTIPOHABILIDAD):
    case SUCCESS(ACTION_TYPES.UPDATE_RELACIONTIPOHABILIDAD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RELACIONTIPOHABILIDAD):
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

const apiUrl = 'api/relacion-tipo-habilidads';

// Actions

export const getEntities: ICrudGetAllAction<IRelacionTipoHabilidad> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD_LIST,
    payload: axios.get<IRelacionTipoHabilidad>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IRelacionTipoHabilidad> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RELACIONTIPOHABILIDAD,
    payload: axios.get<IRelacionTipoHabilidad>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRelacionTipoHabilidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELACIONTIPOHABILIDAD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRelacionTipoHabilidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RELACIONTIPOHABILIDAD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRelacionTipoHabilidad> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RELACIONTIPOHABILIDAD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
