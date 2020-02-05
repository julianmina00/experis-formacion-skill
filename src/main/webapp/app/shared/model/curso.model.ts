import { Moment } from 'moment';
import { ICursoPlanFormativo } from 'app/shared/model/curso-plan-formativo.model';
import { ICursoUsuario } from 'app/shared/model/curso-usuario.model';
import { IContenidoCurso } from 'app/shared/model/contenido-curso.model';

export interface ICurso {
  id?: number;
  descripcion?: string;
  descripcionLarga?: string;
  fechaInicio?: Moment;
  fechaFin?: Moment;
  telematicoPresencial?: string;
  hora?: Moment;
  ubicacion?: string;
  numeroHoras?: number;
  cursoPlanFormativos?: ICursoPlanFormativo[];
  cursoUsuarios?: ICursoUsuario[];
  contenidoCursos?: IContenidoCurso[];
}

export const defaultValue: Readonly<ICurso> = {};
