import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPlanFormativoUsuario, defaultValue } from 'app/shared/model/plan-formativo-usuario.model';

export const ACTION_TYPES = {
  FETCH_PLANFORMATIVOUSUARIO_LIST: 'planFormativoUsuario/FETCH_PLANFORMATIVOUSUARIO_LIST',
  FETCH_PLANFORMATIVOUSUARIO: 'planFormativoUsuario/FETCH_PLANFORMATIVOUSUARIO',
  CREATE_PLANFORMATIVOUSUARIO: 'planFormativoUsuario/CREATE_PLANFORMATIVOUSUARIO',
  UPDATE_PLANFORMATIVOUSUARIO: 'planFormativoUsuario/UPDATE_PLANFORMATIVOUSUARIO',
  DELETE_PLANFORMATIVOUSUARIO: 'planFormativoUsuario/DELETE_PLANFORMATIVOUSUARIO',
  RESET: 'planFormativoUsuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPlanFormativoUsuario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PlanFormativoUsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: PlanFormativoUsuarioState = initialState, action): PlanFormativoUsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PLANFORMATIVOUSUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_PLANFORMATIVOUSUARIO):
    case REQUEST(ACTION_TYPES.DELETE_PLANFORMATIVOUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO):
    case FAILURE(ACTION_TYPES.CREATE_PLANFORMATIVOUSUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_PLANFORMATIVOUSUARIO):
    case FAILURE(ACTION_TYPES.DELETE_PLANFORMATIVOUSUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PLANFORMATIVOUSUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_PLANFORMATIVOUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PLANFORMATIVOUSUARIO):
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

const apiUrl = 'api/plan-formativo-usuarios';

// Actions

export const getEntities: ICrudGetAllAction<IPlanFormativoUsuario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO_LIST,
  payload: axios.get<IPlanFormativoUsuario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPlanFormativoUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PLANFORMATIVOUSUARIO,
    payload: axios.get<IPlanFormativoUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPlanFormativoUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PLANFORMATIVOUSUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPlanFormativoUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PLANFORMATIVOUSUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPlanFormativoUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PLANFORMATIVOUSUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
