import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICursoUsuario, defaultValue } from 'app/shared/model/curso-usuario.model';

export const ACTION_TYPES = {
  FETCH_CURSOUSUARIO_LIST: 'cursoUsuario/FETCH_CURSOUSUARIO_LIST',
  FETCH_CURSOUSUARIO: 'cursoUsuario/FETCH_CURSOUSUARIO',
  CREATE_CURSOUSUARIO: 'cursoUsuario/CREATE_CURSOUSUARIO',
  UPDATE_CURSOUSUARIO: 'cursoUsuario/UPDATE_CURSOUSUARIO',
  DELETE_CURSOUSUARIO: 'cursoUsuario/DELETE_CURSOUSUARIO',
  RESET: 'cursoUsuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICursoUsuario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CursoUsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: CursoUsuarioState = initialState, action): CursoUsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CURSOUSUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CURSOUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CURSOUSUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_CURSOUSUARIO):
    case REQUEST(ACTION_TYPES.DELETE_CURSOUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CURSOUSUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CURSOUSUARIO):
    case FAILURE(ACTION_TYPES.CREATE_CURSOUSUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_CURSOUSUARIO):
    case FAILURE(ACTION_TYPES.DELETE_CURSOUSUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURSOUSUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURSOUSUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CURSOUSUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_CURSOUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CURSOUSUARIO):
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

const apiUrl = 'api/curso-usuarios';

// Actions

export const getEntities: ICrudGetAllAction<ICursoUsuario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CURSOUSUARIO_LIST,
  payload: axios.get<ICursoUsuario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICursoUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CURSOUSUARIO,
    payload: axios.get<ICursoUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICursoUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CURSOUSUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICursoUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CURSOUSUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICursoUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CURSOUSUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
