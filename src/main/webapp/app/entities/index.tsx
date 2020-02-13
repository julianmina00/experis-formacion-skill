import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Curso from './curso';
import PlanFormativo from './plan-formativo';
import TipoHabilidad from './tipo-habilidad';
import TipoInteres from './tipo-interes';
import Usuario from './usuario';
import Habilidad from './habilidad';
import Interes from './interes';
import CursoPlanFormativo from './curso-plan-formativo';
import PerfilPlanFormativo from './perfil-plan-formativo';
import CursoUsuario from './curso-usuario';
import HabilidadUsuario from './habilidad-usuario';
import InteresUsuario from './interes-usuario';
import PlanFormativoUsuario from './plan-formativo-usuario';
import ContenidoCurso from './contenido-curso';
import RelacionTipoHabilidad from './relacion-tipo-habilidad';
import RelacionTipoInteres from './relacion-tipo-interes';
import NivelIdioma from './nivel-idioma';
import IdiomaUsuario from './idioma-usuario';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}curso`} component={Curso} />
      <ErrorBoundaryRoute path={`${match.url}plan-formativo`} component={PlanFormativo} />
      <ErrorBoundaryRoute path={`${match.url}tipo-habilidad`} component={TipoHabilidad} />
      <ErrorBoundaryRoute path={`${match.url}tipo-interes`} component={TipoInteres} />
      <ErrorBoundaryRoute path={`${match.url}usuario`} component={Usuario} />
      <ErrorBoundaryRoute path={`${match.url}habilidad`} component={Habilidad} />
      <ErrorBoundaryRoute path={`${match.url}interes`} component={Interes} />
      <ErrorBoundaryRoute path={`${match.url}curso-plan-formativo`} component={CursoPlanFormativo} />
      <ErrorBoundaryRoute path={`${match.url}perfil-plan-formativo`} component={PerfilPlanFormativo} />
      <ErrorBoundaryRoute path={`${match.url}curso-usuario`} component={CursoUsuario} />
      <ErrorBoundaryRoute path={`${match.url}habilidad-usuario`} component={HabilidadUsuario} />
      <ErrorBoundaryRoute path={`${match.url}interes-usuario`} component={InteresUsuario} />
      <ErrorBoundaryRoute path={`${match.url}plan-formativo-usuario`} component={PlanFormativoUsuario} />
      <ErrorBoundaryRoute path={`${match.url}contenido-curso`} component={ContenidoCurso} />
      <ErrorBoundaryRoute path={`${match.url}relacion-tipo-habilidad`} component={RelacionTipoHabilidad} />
      <ErrorBoundaryRoute path={`${match.url}relacion-tipo-interes`} component={RelacionTipoInteres} />
      <ErrorBoundaryRoute path={`${match.url}nivel-idioma`} component={NivelIdioma} />
      <ErrorBoundaryRoute path={`${match.url}idioma-usuario`} component={IdiomaUsuario} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
