import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IContenidoCurso, defaultValue } from 'app/shared/model/contenido-curso.model';

export const ACTION_TYPES = {
  FETCH_CONTENIDOCURSO_LIST: 'contenidoCurso/FETCH_CONTENIDOCURSO_LIST',
  FETCH_CONTENIDOCURSO: 'contenidoCurso/FETCH_CONTENIDOCURSO',
  CREATE_CONTENIDOCURSO: 'contenidoCurso/CREATE_CONTENIDOCURSO',
  UPDATE_CONTENIDOCURSO: 'contenidoCurso/UPDATE_CONTENIDOCURSO',
  DELETE_CONTENIDOCURSO: 'contenidoCurso/DELETE_CONTENIDOCURSO',
  RESET: 'contenidoCurso/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IContenidoCurso>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ContenidoCursoState = Readonly<typeof initialState>;

// Reducer

export default (state: ContenidoCursoState = initialState, action): ContenidoCursoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CONTENIDOCURSO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CONTENIDOCURSO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CONTENIDOCURSO):
    case REQUEST(ACTION_TYPES.UPDATE_CONTENIDOCURSO):
    case REQUEST(ACTION_TYPES.DELETE_CONTENIDOCURSO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CONTENIDOCURSO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CONTENIDOCURSO):
    case FAILURE(ACTION_TYPES.CREATE_CONTENIDOCURSO):
    case FAILURE(ACTION_TYPES.UPDATE_CONTENIDOCURSO):
    case FAILURE(ACTION_TYPES.DELETE_CONTENIDOCURSO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONTENIDOCURSO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONTENIDOCURSO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CONTENIDOCURSO):
    case SUCCESS(ACTION_TYPES.UPDATE_CONTENIDOCURSO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CONTENIDOCURSO):
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

const apiUrl = 'api/contenido-cursos';

// Actions

export const getEntities: ICrudGetAllAction<IContenidoCurso> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CONTENIDOCURSO_LIST,
    payload: axios.get<IContenidoCurso>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IContenidoCurso> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CONTENIDOCURSO,
    payload: axios.get<IContenidoCurso>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IContenidoCurso> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CONTENIDOCURSO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IContenidoCurso> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CONTENIDOCURSO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IContenidoCurso> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CONTENIDOCURSO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
