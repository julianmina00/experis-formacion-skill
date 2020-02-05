import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Curso from './curso';
import CursoDetail from './curso-detail';
import CursoUpdate from './curso-update';
import CursoDeleteDialog from './curso-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CursoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CursoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CursoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CursoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Curso} />
    </Switch>
  </>
);

export default Routes;
