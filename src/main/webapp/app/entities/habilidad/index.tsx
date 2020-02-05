import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Habilidad from './habilidad';
import HabilidadDetail from './habilidad-detail';
import HabilidadUpdate from './habilidad-update';
import HabilidadDeleteDialog from './habilidad-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HabilidadDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HabilidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HabilidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HabilidadDetail} />
      <ErrorBoundaryRoute path={match.url} component={Habilidad} />
    </Switch>
  </>
);

export default Routes;
