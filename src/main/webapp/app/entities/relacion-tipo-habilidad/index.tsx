import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RelacionTipoHabilidad from './relacion-tipo-habilidad';
import RelacionTipoHabilidadDetail from './relacion-tipo-habilidad-detail';
import RelacionTipoHabilidadUpdate from './relacion-tipo-habilidad-update';
import RelacionTipoHabilidadDeleteDialog from './relacion-tipo-habilidad-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RelacionTipoHabilidadDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RelacionTipoHabilidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RelacionTipoHabilidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RelacionTipoHabilidadDetail} />
      <ErrorBoundaryRoute path={match.url} component={RelacionTipoHabilidad} />
    </Switch>
  </>
);

export default Routes;
