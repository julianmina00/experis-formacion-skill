import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './contenido-curso.reducer';
import { IContenidoCurso } from 'app/shared/model/contenido-curso.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContenidoCursoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContenidoCursoDetail = (props: IContenidoCursoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { contenidoCursoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.contenidoCurso.detail.title">ContenidoCurso</Translate> [
          <b>{contenidoCursoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="habilidadInteres">
              <Translate contentKey="experisFormacionApp.contenidoCurso.habilidadInteres">Habilidad Interes</Translate>
            </span>
          </dt>
          <dd>{contenidoCursoEntity.habilidadInteres}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.contenidoCurso.curso">Curso</Translate>
          </dt>
          <dd>{contenidoCursoEntity.cursoId ? contenidoCursoEntity.cursoId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.contenidoCurso.interes">Interes</Translate>
          </dt>
          <dd>{contenidoCursoEntity.interesId ? contenidoCursoEntity.interesId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.contenidoCurso.habilidad">Habilidad</Translate>
          </dt>
          <dd>{contenidoCursoEntity.habilidadId ? contenidoCursoEntity.habilidadId : ''}</dd>
        </dl>
        <Button tag={Link} to="/contenido-curso" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contenido-curso/${contenidoCursoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ contenidoCurso }: IRootState) => ({
  contenidoCursoEntity: contenidoCurso.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContenidoCursoDetail);
