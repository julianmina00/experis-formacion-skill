import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RelacionTipoInteres from './relacion-tipo-interes';
import RelacionTipoInteresDetail from './relacion-tipo-interes-detail';
import RelacionTipoInteresUpdate from './relacion-tipo-interes-update';
import RelacionTipoInteresDeleteDialog from './relacion-tipo-interes-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RelacionTipoInteresDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RelacionTipoInteresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RelacionTipoInteresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RelacionTipoInteresDetail} />
      <ErrorBoundaryRoute path={match.url} component={RelacionTipoInteres} />
    </Switch>
  </>
);

export default Routes;
