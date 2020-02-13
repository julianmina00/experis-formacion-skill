import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IdiomaUsuario from './idioma-usuario';
import IdiomaUsuarioDetail from './idioma-usuario-detail';
import IdiomaUsuarioUpdate from './idioma-usuario-update';
import IdiomaUsuarioDeleteDialog from './idioma-usuario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={IdiomaUsuarioDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IdiomaUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IdiomaUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IdiomaUsuarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={IdiomaUsuario} />
    </Switch>
  </>
);

export default Routes;
