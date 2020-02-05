import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InteresUsuario from './interes-usuario';
import InteresUsuarioDetail from './interes-usuario-detail';
import InteresUsuarioUpdate from './interes-usuario-update';
import InteresUsuarioDeleteDialog from './interes-usuario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InteresUsuarioDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InteresUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InteresUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InteresUsuarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={InteresUsuario} />
    </Switch>
  </>
);

export default Routes;
