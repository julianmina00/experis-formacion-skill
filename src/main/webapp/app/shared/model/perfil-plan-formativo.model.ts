export interface IPerfilPlanFormativo {
  id?: number;
  interesHabilidad?: string;
  habilidadId?: number;
  interesId?: number;
  planFormativoId?: number;
}

export const defaultValue: Readonly<IPerfilPlanFormativo> = {};
