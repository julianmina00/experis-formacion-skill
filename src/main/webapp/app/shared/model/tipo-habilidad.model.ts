import { IHabilidad } from 'app/shared/model/habilidad.model';

export interface ITipoHabilidad {
  id?: number;
  descripcion?: string;
  descripcionLarga?: string;
  habilidads?: IHabilidad[];
}

export const defaultValue: Readonly<ITipoHabilidad> = {};
