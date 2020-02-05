import { IPerfilPlanFormativo } from 'app/shared/model/perfil-plan-formativo.model';
import { IHabilidadUsuario } from 'app/shared/model/habilidad-usuario.model';
import { IContenidoCurso } from 'app/shared/model/contenido-curso.model';

export interface IHabilidad {
  id?: number;
  descripcion?: string;
  descripcionLarga?: string;
  perfilPlanFormativos?: IPerfilPlanFormativo[];
  habilidadUsuarios?: IHabilidadUsuario[];
  contenidoCursos?: IContenidoCurso[];
  tipoHabilidadId?: number;
}

export const defaultValue: Readonly<IHabilidad> = {};
