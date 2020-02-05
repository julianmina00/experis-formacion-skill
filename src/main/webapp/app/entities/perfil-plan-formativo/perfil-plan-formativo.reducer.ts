import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPerfilPlanFormativo, defaultValue } from 'app/shared/model/perfil-plan-formativo.model';

export const ACTION_TYPES = {
  FETCH_PERFILPLANFORMATIVO_LIST: 'perfilPlanFormativo/FETCH_PERFILPLANFORMATIVO_LIST',
  FETCH_PERFILPLANFORMATIVO: 'perfilPlanFormativo/FETCH_PERFILPLANFORMATIVO',
  CREATE_PERFILPLANFORMATIVO: 'perfilPlanFormativo/CREATE_PERFILPLANFORMATIVO',
  UPDATE_PERFILPLANFORMATIVO: 'perfilPlanFormativo/UPDATE_PERFILPLANFORMATIVO',
  DELETE_PERFILPLANFORMATIVO: 'perfilPlanFormativo/DELETE_PERFILPLANFORMATIVO',
  RESET: 'perfilPlanFormativo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPerfilPlanFormativo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PerfilPlanFormativoState = Readonly<typeof initialState>;

// Reducer

export default (state: PerfilPlanFormativoState = initialState, action): PerfilPlanFormativoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PERFILPLANFORMATIVO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PERFILPLANFORMATIVO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PERFILPLANFORMATIVO):
    case REQUEST(ACTION_TYPES.UPDATE_PERFILPLANFORMATIVO):
    case REQUEST(ACTION_TYPES.DELETE_PERFILPLANFORMATIVO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PERFILPLANFORMATIVO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PERFILPLANFORMATIVO):
    case FAILURE(ACTION_TYPES.CREATE_PERFILPLANFORMATIVO):
    case FAILURE(ACTION_TYPES.UPDATE_PERFILPLANFORMATIVO):
    case FAILURE(ACTION_TYPES.DELETE_PERFILPLANFORMATIVO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERFILPLANFORMATIVO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERFILPLANFORMATIVO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PERFILPLANFORMATIVO):
    case SUCCESS(ACTION_TYPES.UPDATE_PERFILPLANFORMATIVO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PERFILPLANFORMATIVO):
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

const apiUrl = 'api/perfil-plan-formativos';

// Actions

export const getEntities: ICrudGetAllAction<IPerfilPlanFormativo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PERFILPLANFORMATIVO_LIST,
  payload: axios.get<IPerfilPlanFormativo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPerfilPlanFormativo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PERFILPLANFORMATIVO,
    payload: axios.get<IPerfilPlanFormativo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPerfilPlanFormativo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PERFILPLANFORMATIVO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPerfilPlanFormativo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PERFILPLANFORMATIVO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPerfilPlanFormativo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PERFILPLANFORMATIVO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
