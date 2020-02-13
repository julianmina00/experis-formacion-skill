export interface IRelacionTipoHabilidad {
  id?: number;
  profundidad?: number;
  padreId?: number;
  hijaId?: number;
}

export const defaultValue: Readonly<IRelacionTipoHabilidad> = {};
