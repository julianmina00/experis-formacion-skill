import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PerfilPlanFormativo from './perfil-plan-formativo';
import PerfilPlanFormativoDetail from './perfil-plan-formativo-detail';
import PerfilPlanFormativoUpdate from './perfil-plan-formativo-update';
import PerfilPlanFormativoDeleteDialog from './perfil-plan-formativo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PerfilPlanFormativoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PerfilPlanFormativoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PerfilPlanFormativoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PerfilPlanFormativoDetail} />
      <ErrorBoundaryRoute path={match.url} component={PerfilPlanFormativo} />
    </Switch>
  </>
);

export default Routes;
