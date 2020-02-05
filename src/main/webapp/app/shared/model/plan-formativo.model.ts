import { Moment } from 'moment';
import { ICursoPlanFormativo } from 'app/shared/model/curso-plan-formativo.model';
import { IPerfilPlanFormativo } from 'app/shared/model/perfil-plan-formativo.model';
import { IPlanFormativoUsuario } from 'app/shared/model/plan-formativo-usuario.model';

export interface IPlanFormativo {
  id?: number;
  descripcion?: string;
  descripcionLarga?: string;
  fechaInicio?: Moment;
  fechaFin?: Moment;
  cursoPlanFormativos?: ICursoPlanFormativo[];
  perfilPlanFormativos?: IPerfilPlanFormativo[];
  planFormativoUsuarios?: IPlanFormativoUsuario[];
}

export const defaultValue: Readonly<IPlanFormativo> = {};
