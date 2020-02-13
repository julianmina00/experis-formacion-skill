import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INivelIdioma, defaultValue } from 'app/shared/model/nivel-idioma.model';

export const ACTION_TYPES = {
  FETCH_NIVELIDIOMA_LIST: 'nivelIdioma/FETCH_NIVELIDIOMA_LIST',
  FETCH_NIVELIDIOMA: 'nivelIdioma/FETCH_NIVELIDIOMA',
  CREATE_NIVELIDIOMA: 'nivelIdioma/CREATE_NIVELIDIOMA',
  UPDATE_NIVELIDIOMA: 'nivelIdioma/UPDATE_NIVELIDIOMA',
  DELETE_NIVELIDIOMA: 'nivelIdioma/DELETE_NIVELIDIOMA',
  RESET: 'nivelIdioma/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INivelIdioma>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NivelIdiomaState = Readonly<typeof initialState>;

// Reducer

export default (state: NivelIdiomaState = initialState, action): NivelIdiomaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NIVELIDIOMA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NIVELIDIOMA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NIVELIDIOMA):
    case REQUEST(ACTION_TYPES.UPDATE_NIVELIDIOMA):
    case REQUEST(ACTION_TYPES.DELETE_NIVELIDIOMA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NIVELIDIOMA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NIVELIDIOMA):
    case FAILURE(ACTION_TYPES.CREATE_NIVELIDIOMA):
    case FAILURE(ACTION_TYPES.UPDATE_NIVELIDIOMA):
    case FAILURE(ACTION_TYPES.DELETE_NIVELIDIOMA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NIVELIDIOMA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NIVELIDIOMA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NIVELIDIOMA):
    case SUCCESS(ACTION_TYPES.UPDATE_NIVELIDIOMA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NIVELIDIOMA):
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

const apiUrl = 'api/nivel-idiomas';

// Actions

export const getEntities: ICrudGetAllAction<INivelIdioma> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NIVELIDIOMA_LIST,
  payload: axios.get<INivelIdioma>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INivelIdioma> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NIVELIDIOMA,
    payload: axios.get<INivelIdioma>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INivelIdioma> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NIVELIDIOMA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INivelIdioma> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NIVELIDIOMA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INivelIdioma> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NIVELIDIOMA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
