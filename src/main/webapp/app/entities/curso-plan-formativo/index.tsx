import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CursoPlanFormativo from './curso-plan-formativo';
import CursoPlanFormativoDetail from './curso-plan-formativo-detail';
import CursoPlanFormativoUpdate from './curso-plan-formativo-update';
import CursoPlanFormativoDeleteDialog from './curso-plan-formativo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CursoPlanFormativoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CursoPlanFormativoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CursoPlanFormativoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CursoPlanFormativoDetail} />
      <ErrorBoundaryRoute path={match.url} component={CursoPlanFormativo} />
    </Switch>
  </>
);

export default Routes;
