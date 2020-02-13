import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NivelIdioma from './nivel-idioma';
import NivelIdiomaDetail from './nivel-idioma-detail';
import NivelIdiomaUpdate from './nivel-idioma-update';
import NivelIdiomaDeleteDialog from './nivel-idioma-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NivelIdiomaDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NivelIdiomaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NivelIdiomaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NivelIdiomaDetail} />
      <ErrorBoundaryRoute path={match.url} component={NivelIdioma} />
    </Switch>
  </>
);

export default Routes;
