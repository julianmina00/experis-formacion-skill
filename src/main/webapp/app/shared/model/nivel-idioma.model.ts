import { IIdiomaUsuario } from 'app/shared/model/idioma-usuario.model';

export interface INivelIdioma {
  id?: number;
  nivel?: string;
  descripcion?: string;
  idiomaUsuarios?: IIdiomaUsuario[];
}

export const defaultValue: Readonly<INivelIdioma> = {};
