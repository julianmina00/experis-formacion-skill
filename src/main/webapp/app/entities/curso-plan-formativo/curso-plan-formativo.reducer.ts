import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICursoPlanFormativo, defaultValue } from 'app/shared/model/curso-plan-formativo.model';

export const ACTION_TYPES = {
  FETCH_CURSOPLANFORMATIVO_LIST: 'cursoPlanFormativo/FETCH_CURSOPLANFORMATIVO_LIST',
  FETCH_CURSOPLANFORMATIVO: 'cursoPlanFormativo/FETCH_CURSOPLANFORMATIVO',
  CREATE_CURSOPLANFORMATIVO: 'cursoPlanFormativo/CREATE_CURSOPLANFORMATIVO',
  UPDATE_CURSOPLANFORMATIVO: 'cursoPlanFormativo/UPDATE_CURSOPLANFORMATIVO',
  DELETE_CURSOPLANFORMATIVO: 'cursoPlanFormativo/DELETE_CURSOPLANFORMATIVO',
  RESET: 'cursoPlanFormativo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICursoPlanFormativo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CursoPlanFormativoState = Readonly<typeof initialState>;

// Reducer

export default (state: CursoPlanFormativoState = initialState, action): CursoPlanFormativoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CURSOPLANFORMATIVO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CURSOPLANFORMATIVO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CURSOPLANFORMATIVO):
    case REQUEST(ACTION_TYPES.UPDATE_CURSOPLANFORMATIVO):
    case REQUEST(ACTION_TYPES.DELETE_CURSOPLANFORMATIVO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CURSOPLANFORMATIVO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CURSOPLANFORMATIVO):
    case FAILURE(ACTION_TYPES.CREATE_CURSOPLANFORMATIVO):
    case FAILURE(ACTION_TYPES.UPDATE_CURSOPLANFORMATIVO):
    case FAILURE(ACTION_TYPES.DELETE_CURSOPLANFORMATIVO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURSOPLANFORMATIVO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURSOPLANFORMATIVO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CURSOPLANFORMATIVO):
    case SUCCESS(ACTION_TYPES.UPDATE_CURSOPLANFORMATIVO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CURSOPLANFORMATIVO):
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

const apiUrl = 'api/curso-plan-formativos';

// Actions

export const getEntities: ICrudGetAllAction<ICursoPlanFormativo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CURSOPLANFORMATIVO_LIST,
  payload: axios.get<ICursoPlanFormativo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICursoPlanFormativo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CURSOPLANFORMATIVO,
    payload: axios.get<ICursoPlanFormativo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICursoPlanFormativo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CURSOPLANFORMATIVO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICursoPlanFormativo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CURSOPLANFORMATIVO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICursoPlanFormativo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CURSOPLANFORMATIVO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
