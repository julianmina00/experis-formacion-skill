import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIdiomaUsuario, defaultValue } from 'app/shared/model/idioma-usuario.model';

export const ACTION_TYPES = {
  FETCH_IDIOMAUSUARIO_LIST: 'idiomaUsuario/FETCH_IDIOMAUSUARIO_LIST',
  FETCH_IDIOMAUSUARIO: 'idiomaUsuario/FETCH_IDIOMAUSUARIO',
  CREATE_IDIOMAUSUARIO: 'idiomaUsuario/CREATE_IDIOMAUSUARIO',
  UPDATE_IDIOMAUSUARIO: 'idiomaUsuario/UPDATE_IDIOMAUSUARIO',
  DELETE_IDIOMAUSUARIO: 'idiomaUsuario/DELETE_IDIOMAUSUARIO',
  RESET: 'idiomaUsuario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIdiomaUsuario>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type IdiomaUsuarioState = Readonly<typeof initialState>;

// Reducer

export default (state: IdiomaUsuarioState = initialState, action): IdiomaUsuarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IDIOMAUSUARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IDIOMAUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IDIOMAUSUARIO):
    case REQUEST(ACTION_TYPES.UPDATE_IDIOMAUSUARIO):
    case REQUEST(ACTION_TYPES.DELETE_IDIOMAUSUARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_IDIOMAUSUARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IDIOMAUSUARIO):
    case FAILURE(ACTION_TYPES.CREATE_IDIOMAUSUARIO):
    case FAILURE(ACTION_TYPES.UPDATE_IDIOMAUSUARIO):
    case FAILURE(ACTION_TYPES.DELETE_IDIOMAUSUARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_IDIOMAUSUARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_IDIOMAUSUARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IDIOMAUSUARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_IDIOMAUSUARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IDIOMAUSUARIO):
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

const apiUrl = 'api/idioma-usuarios';

// Actions

export const getEntities: ICrudGetAllAction<IIdiomaUsuario> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_IDIOMAUSUARIO_LIST,
    payload: axios.get<IIdiomaUsuario>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IIdiomaUsuario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IDIOMAUSUARIO,
    payload: axios.get<IIdiomaUsuario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIdiomaUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IDIOMAUSUARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIdiomaUsuario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IDIOMAUSUARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIdiomaUsuario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IDIOMAUSUARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
