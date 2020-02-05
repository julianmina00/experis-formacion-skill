import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './curso.reducer';
import { ICurso } from 'app/shared/model/curso.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICursoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursoDetail = (props: ICursoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cursoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.curso.detail.title">Curso</Translate> [<b>{cursoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.curso.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{cursoEntity.descripcion}</dd>
          <dt>
            <span id="descripcionLarga">
              <Translate contentKey="experisFormacionApp.curso.descripcionLarga">Descripcion Larga</Translate>
            </span>
          </dt>
          <dd>{cursoEntity.descripcionLarga}</dd>
          <dt>
            <span id="fechaInicio">
              <Translate contentKey="experisFormacionApp.curso.fechaInicio">Fecha Inicio</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cursoEntity.fechaInicio} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="fechaFin">
              <Translate contentKey="experisFormacionApp.curso.fechaFin">Fecha Fin</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cursoEntity.fechaFin} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="telematicoPresencial">
              <Translate contentKey="experisFormacionApp.curso.telematicoPresencial">Telematico Presencial</Translate>
            </span>
          </dt>
          <dd>{cursoEntity.telematicoPresencial}</dd>
          <dt>
            <span id="hora">
              <Translate contentKey="experisFormacionApp.curso.hora">Hora</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cursoEntity.hora} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="ubicacion">
              <Translate contentKey="experisFormacionApp.curso.ubicacion">Ubicacion</Translate>
            </span>
          </dt>
          <dd>{cursoEntity.ubicacion}</dd>
          <dt>
            <span id="numeroHoras">
              <Translate contentKey="experisFormacionApp.curso.numeroHoras">Numero Horas</Translate>
            </span>
          </dt>
          <dd>{cursoEntity.numeroHoras}</dd>
        </dl>
        <Button tag={Link} to="/curso" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/curso/${cursoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ curso }: IRootState) => ({
  cursoEntity: curso.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoDetail);
