import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import curso, {
  CursoState
} from 'app/entities/curso/curso.reducer';
// prettier-ignore
import planFormativo, {
  PlanFormativoState
} from 'app/entities/plan-formativo/plan-formativo.reducer';
// prettier-ignore
import tipoHabilidad, {
  TipoHabilidadState
} from 'app/entities/tipo-habilidad/tipo-habilidad.reducer';
// prettier-ignore
import tipoInteres, {
  TipoInteresState
} from 'app/entities/tipo-interes/tipo-interes.reducer';
// prettier-ignore
import usuario, {
  UsuarioState
} from 'app/entities/usuario/usuario.reducer';
// prettier-ignore
import habilidad, {
  HabilidadState
} from 'app/entities/habilidad/habilidad.reducer';
// prettier-ignore
import interes, {
  InteresState
} from 'app/entities/interes/interes.reducer';
// prettier-ignore
import cursoPlanFormativo, {
  CursoPlanFormativoState
} from 'app/entities/curso-plan-formativo/curso-plan-formativo.reducer';
// prettier-ignore
import perfilPlanFormativo, {
  PerfilPlanFormativoState
} from 'app/entities/perfil-plan-formativo/perfil-plan-formativo.reducer';
// prettier-ignore
import cursoUsuario, {
  CursoUsuarioState
} from 'app/entities/curso-usuario/curso-usuario.reducer';
// prettier-ignore
import habilidadUsuario, {
  HabilidadUsuarioState
} from 'app/entities/habilidad-usuario/habilidad-usuario.reducer';
// prettier-ignore
import interesUsuario, {
  InteresUsuarioState
} from 'app/entities/interes-usuario/interes-usuario.reducer';
// prettier-ignore
import planFormativoUsuario, {
  PlanFormativoUsuarioState
} from 'app/entities/plan-formativo-usuario/plan-formativo-usuario.reducer';
// prettier-ignore
import contenidoCurso, {
  ContenidoCursoState
} from 'app/entities/contenido-curso/contenido-curso.reducer';
// prettier-ignore
import relacionTipoHabilidad, {
  RelacionTipoHabilidadState
} from 'app/entities/relacion-tipo-habilidad/relacion-tipo-habilidad.reducer';
// prettier-ignore
import relacionTipoInteres, {
  RelacionTipoInteresState
} from 'app/entities/relacion-tipo-interes/relacion-tipo-interes.reducer';
// prettier-ignore
import nivelIdioma, {
  NivelIdiomaState
} from 'app/entities/nivel-idioma/nivel-idioma.reducer';
// prettier-ignore
import idiomaUsuario, {
  IdiomaUsuarioState
} from 'app/entities/idioma-usuario/idioma-usuario.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly curso: CursoState;
  readonly planFormativo: PlanFormativoState;
  readonly tipoHabilidad: TipoHabilidadState;
  readonly tipoInteres: TipoInteresState;
  readonly usuario: UsuarioState;
  readonly habilidad: HabilidadState;
  readonly interes: InteresState;
  readonly cursoPlanFormativo: CursoPlanFormativoState;
  readonly perfilPlanFormativo: PerfilPlanFormativoState;
  readonly cursoUsuario: CursoUsuarioState;
  readonly habilidadUsuario: HabilidadUsuarioState;
  readonly interesUsuario: InteresUsuarioState;
  readonly planFormativoUsuario: PlanFormativoUsuarioState;
  readonly contenidoCurso: ContenidoCursoState;
  readonly relacionTipoHabilidad: RelacionTipoHabilidadState;
  readonly relacionTipoInteres: RelacionTipoInteresState;
  readonly nivelIdioma: NivelIdiomaState;
  readonly idiomaUsuario: IdiomaUsuarioState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  curso,
  planFormativo,
  tipoHabilidad,
  tipoInteres,
  usuario,
  habilidad,
  interes,
  cursoPlanFormativo,
  perfilPlanFormativo,
  cursoUsuario,
  habilidadUsuario,
  interesUsuario,
  planFormativoUsuario,
  contenidoCurso,
  relacionTipoHabilidad,
  relacionTipoInteres,
  nivelIdioma,
  idiomaUsuario,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
