export interface IContenidoCurso {
  id?: number;
  habilidadInteres?: string;
  cursoId?: number;
  interesId?: number;
  habilidadId?: number;
}

export const defaultValue: Readonly<IContenidoCurso> = {};
