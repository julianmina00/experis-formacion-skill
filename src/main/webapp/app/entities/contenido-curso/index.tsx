import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContenidoCurso from './contenido-curso';
import ContenidoCursoDetail from './contenido-curso-detail';
import ContenidoCursoUpdate from './contenido-curso-update';
import ContenidoCursoDeleteDialog from './contenido-curso-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContenidoCursoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContenidoCursoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContenidoCursoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContenidoCursoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContenidoCurso} />
    </Switch>
  </>
);

export default Routes;
