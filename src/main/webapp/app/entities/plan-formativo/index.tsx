import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PlanFormativo from './plan-formativo';
import PlanFormativoDetail from './plan-formativo-detail';
import PlanFormativoUpdate from './plan-formativo-update';
import PlanFormativoDeleteDialog from './plan-formativo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PlanFormativoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlanFormativoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlanFormativoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlanFormativoDetail} />
      <ErrorBoundaryRoute path={match.url} component={PlanFormativo} />
    </Switch>
  </>
);

export default Routes;
