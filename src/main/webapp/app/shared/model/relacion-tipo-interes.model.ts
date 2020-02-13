export interface IRelacionTipoInteres {
  id?: number;
  profundidad?: number;
  padreId?: number;
  hijaId?: number;
}

export const defaultValue: Readonly<IRelacionTipoInteres> = {};
