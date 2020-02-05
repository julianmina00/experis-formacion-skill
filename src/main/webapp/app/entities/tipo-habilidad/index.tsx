import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TipoHabilidad from './tipo-habilidad';
import TipoHabilidadDetail from './tipo-habilidad-detail';
import TipoHabilidadUpdate from './tipo-habilidad-update';
import TipoHabilidadDeleteDialog from './tipo-habilidad-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TipoHabilidadDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TipoHabilidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TipoHabilidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TipoHabilidadDetail} />
      <ErrorBoundaryRoute path={match.url} component={TipoHabilidad} />
    </Switch>
  </>
);

export default Routes;
