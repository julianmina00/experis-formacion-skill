import { ICursoUsuario } from 'app/shared/model/curso-usuario.model';
import { IHabilidadUsuario } from 'app/shared/model/habilidad-usuario.model';
import { IInteresUsuario } from 'app/shared/model/interes-usuario.model';
import { IPlanFormativoUsuario } from 'app/shared/model/plan-formativo-usuario.model';
import { IIdiomaUsuario } from 'app/shared/model/idioma-usuario.model';

export interface IUsuario {
  id?: number;
  documento?: string;
  tipoDocumento?: string;
  nombre?: string;
  apellidos?: string;
  email?: string;
  telefono?: string;
  rol?: string;
  proyecto?: string;
  compania?: string;
  ubicacion?: string;
  managerNombre?: string;
  managerEmail?: string;
  talentMentorNombre?: string;
  talentMentorEmail?: string;
  cursoUsuarios?: ICursoUsuario[];
  habilidadUsuarios?: IHabilidadUsuario[];
  interesUsuarios?: IInteresUsuario[];
  planFormativoUsuarios?: IPlanFormativoUsuario[];
  idiomaUsuarios?: IIdiomaUsuario[];
}

export const defaultValue: Readonly<IUsuario> = {};
