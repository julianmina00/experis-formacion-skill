import { IInteres } from 'app/shared/model/interes.model';

export interface ITipoInteres {
  id?: number;
  descripcion?: string;
  descripcionLarga?: string;
  interes?: IInteres[];
}

export const defaultValue: Readonly<ITipoInteres> = {};
