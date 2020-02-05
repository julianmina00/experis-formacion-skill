import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HabilidadUsuario from './habilidad-usuario';
import HabilidadUsuarioDetail from './habilidad-usuario-detail';
import HabilidadUsuarioUpdate from './habilidad-usuario-update';
import HabilidadUsuarioDeleteDialog from './habilidad-usuario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HabilidadUsuarioDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HabilidadUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HabilidadUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HabilidadUsuarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={HabilidadUsuario} />
    </Switch>
  </>
);

export default Routes;
