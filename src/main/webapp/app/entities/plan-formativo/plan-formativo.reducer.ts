import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPlanFormativo, defaultValue } from 'app/shared/model/plan-formativo.model';

export const ACTION_TYPES = {
  FETCH_PLANFORMATIVO_LIST: 'planFormativo/FETCH_PLANFORMATIVO_LIST',
  FETCH_PLANFORMATIVO: 'planFormativo/FETCH_PLANFORMATIVO',
  CREATE_PLANFORMATIVO: 'planFormativo/CREATE_PLANFORMATIVO',
  UPDATE_PLANFORMATIVO: 'planFormativo/UPDATE_PLANFORMATIVO',
  DELETE_PLANFORMATIVO: 'planFormativo/DELETE_PLANFORMATIVO',
  RESET: 'planFormativo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPlanFormativo>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PlanFormativoState = Readonly<typeof initialState>;

// Reducer

export default (state: PlanFormativoState = initialState, action): PlanFormativoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PLANFORMATIVO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PLANFORMATIVO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PLANFORMATIVO):
    case REQUEST(ACTION_TYPES.UPDATE_PLANFORMATIVO):
    case REQUEST(ACTION_TYPES.DELETE_PLANFORMATIVO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PLANFORMATIVO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PLANFORMATIVO):
    case FAILURE(ACTION_TYPES.CREATE_PLANFORMATIVO):
    case FAILURE(ACTION_TYPES.UPDATE_PLANFORMATIVO):
    case FAILURE(ACTION_TYPES.DELETE_PLANFORMATIVO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLANFORMATIVO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLANFORMATIVO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PLANFORMATIVO):
    case SUCCESS(ACTION_TYPES.UPDATE_PLANFORMATIVO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PLANFORMATIVO):
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

const apiUrl = 'api/plan-formativos';

// Actions

export const getEntities: ICrudGetAllAction<IPlanFormativo> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PLANFORMATIVO_LIST,
    payload: axios.get<IPlanFormativo>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPlanFormativo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PLANFORMATIVO,
    payload: axios.get<IPlanFormativo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPlanFormativo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PLANFORMATIVO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPlanFormativo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PLANFORMATIVO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPlanFormativo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PLANFORMATIVO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
