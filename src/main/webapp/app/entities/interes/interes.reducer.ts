import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInteres, defaultValue } from 'app/shared/model/interes.model';

export const ACTION_TYPES = {
  FETCH_INTERES_LIST: 'interes/FETCH_INTERES_LIST',
  FETCH_INTERES: 'interes/FETCH_INTERES',
  CREATE_INTERES: 'interes/CREATE_INTERES',
  UPDATE_INTERES: 'interes/UPDATE_INTERES',
  DELETE_INTERES: 'interes/DELETE_INTERES',
  RESET: 'interes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInteres>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type InteresState = Readonly<typeof initialState>;

// Reducer

export default (state: InteresState = initialState, action): InteresState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INTERES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INTERES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INTERES):
    case REQUEST(ACTION_TYPES.UPDATE_INTERES):
    case REQUEST(ACTION_TYPES.DELETE_INTERES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INTERES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INTERES):
    case FAILURE(ACTION_TYPES.CREATE_INTERES):
    case FAILURE(ACTION_TYPES.UPDATE_INTERES):
    case FAILURE(ACTION_TYPES.DELETE_INTERES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INTERES):
    case SUCCESS(ACTION_TYPES.UPDATE_INTERES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INTERES):
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

const apiUrl = 'api/interes';

// Actions

export const getEntities: ICrudGetAllAction<IInteres> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_INTERES_LIST,
    payload: axios.get<IInteres>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IInteres> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INTERES,
    payload: axios.get<IInteres>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInteres> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INTERES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInteres> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INTERES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInteres> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INTERES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
