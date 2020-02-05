import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Interes from './interes';
import InteresDetail from './interes-detail';
import InteresUpdate from './interes-update';
import InteresDeleteDialog from './interes-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InteresDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InteresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InteresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InteresDetail} />
      <ErrorBoundaryRoute path={match.url} component={Interes} />
    </Switch>
  </>
);

export default Routes;
