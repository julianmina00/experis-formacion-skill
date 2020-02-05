import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Usuario from './usuario';
import UsuarioDetail from './usuario-detail';
import UsuarioUpdate from './usuario-update';
import UsuarioDeleteDialog from './usuario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UsuarioDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UsuarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={Usuario} />
    </Switch>
  </>
);

export default Routes;
