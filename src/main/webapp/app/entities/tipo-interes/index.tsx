import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TipoInteres from './tipo-interes';
import TipoInteresDetail from './tipo-interes-detail';
import TipoInteresUpdate from './tipo-interes-update';
import TipoInteresDeleteDialog from './tipo-interes-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TipoInteresDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TipoInteresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TipoInteresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TipoInteresDetail} />
      <ErrorBoundaryRoute path={match.url} component={TipoInteres} />
    </Switch>
  </>
);

export default Routes;
