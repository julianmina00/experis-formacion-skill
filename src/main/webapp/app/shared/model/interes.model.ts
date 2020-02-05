import { IPerfilPlanFormativo } from 'app/shared/model/perfil-plan-formativo.model';
import { IInteresUsuario } from 'app/shared/model/interes-usuario.model';
import { IContenidoCurso } from 'app/shared/model/contenido-curso.model';

export interface IInteres {
  id?: number;
  descripcion?: string;
  descripcionLarga?: string;
  perfilPlanFormativos?: IPerfilPlanFormativo[];
  interesUsuarios?: IInteresUsuario[];
  contenidoCursos?: IContenidoCurso[];
  tipoInteresId?: number;
}

export const defaultValue: Readonly<IInteres> = {};
