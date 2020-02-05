import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CursoUsuario from './curso-usuario';
import CursoUsuarioDetail from './curso-usuario-detail';
import CursoUsuarioUpdate from './curso-usuario-update';
import CursoUsuarioDeleteDialog from './curso-usuario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CursoUsuarioDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CursoUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CursoUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CursoUsuarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={CursoUsuario} />
    </Switch>
  </>
);

export default Routes;
