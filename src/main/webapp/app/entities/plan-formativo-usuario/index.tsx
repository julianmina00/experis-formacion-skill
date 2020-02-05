import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PlanFormativoUsuario from './plan-formativo-usuario';
import PlanFormativoUsuarioDetail from './plan-formativo-usuario-detail';
import PlanFormativoUsuarioUpdate from './plan-formativo-usuario-update';
import PlanFormativoUsuarioDeleteDialog from './plan-formativo-usuario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PlanFormativoUsuarioDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlanFormativoUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlanFormativoUsuarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlanFormativoUsuarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={PlanFormativoUsuario} />
    </Switch>
  </>
);

export default Routes;
