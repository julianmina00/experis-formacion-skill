import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRelacionTipoInteres, defaultValue } from 'app/shared/model/relacion-tipo-interes.model';

export const ACTION_TYPES = {
  FETCH_RELACIONTIPOINTERES_LIST: 'relacionTipoInteres/FETCH_RELACIONTIPOINTERES_LIST',
  FETCH_RELACIONTIPOINTERES: 'relacionTipoInteres/FETCH_RELACIONTIPOINTERES',
  CREATE_RELACIONTIPOINTERES: 'relacionTipoInteres/CREATE_RELACIONTIPOINTERES',
  UPDATE_RELACIONTIPOINTERES: 'relacionTipoInteres/UPDATE_RELACIONTIPOINTERES',
  DELETE_RELACIONTIPOINTERES: 'relacionTipoInteres/DELETE_RELACIONTIPOINTERES',
  RESET: 'relacionTipoInteres/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRelacionTipoInteres>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type RelacionTipoInteresState = Readonly<typeof initialState>;

// Reducer

export default (state: RelacionTipoInteresState = initialState, action): RelacionTipoInteresState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RELACIONTIPOINTERES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RELACIONTIPOINTERES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RELACIONTIPOINTERES):
    case REQUEST(ACTION_TYPES.UPDATE_RELACIONTIPOINTERES):
    case REQUEST(ACTION_TYPES.DELETE_RELACIONTIPOINTERES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RELACIONTIPOINTERES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RELACIONTIPOINTERES):
    case FAILURE(ACTION_TYPES.CREATE_RELACIONTIPOINTERES):
    case FAILURE(ACTION_TYPES.UPDATE_RELACIONTIPOINTERES):
    case FAILURE(ACTION_TYPES.DELETE_RELACIONTIPOINTERES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELACIONTIPOINTERES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELACIONTIPOINTERES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RELACIONTIPOINTERES):
    case SUCCESS(ACTION_TYPES.UPDATE_RELACIONTIPOINTERES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RELACIONTIPOINTERES):
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

const apiUrl = 'api/relacion-tipo-interes';

// Actions

export const getEntities: ICrudGetAllAction<IRelacionTipoInteres> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_RELACIONTIPOINTERES_LIST,
    payload: axios.get<IRelacionTipoInteres>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IRelacionTipoInteres> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RELACIONTIPOINTERES,
    payload: axios.get<IRelacionTipoInteres>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRelacionTipoInteres> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELACIONTIPOINTERES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRelacionTipoInteres> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RELACIONTIPOINTERES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRelacionTipoInteres> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RELACIONTIPOINTERES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
