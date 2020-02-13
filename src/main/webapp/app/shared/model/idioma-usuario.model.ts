export interface IIdiomaUsuario {
  id?: number;
  idioma?: string;
  usuarioId?: number;
  nivelIdiomaId?: number;
}

export const defaultValue: Readonly<IIdiomaUsuario> = {};
