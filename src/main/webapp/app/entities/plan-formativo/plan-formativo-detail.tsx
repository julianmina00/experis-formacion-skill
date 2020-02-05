import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './plan-formativo.reducer';
import { IPlanFormativo } from 'app/shared/model/plan-formativo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlanFormativoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlanFormativoDetail = (props: IPlanFormativoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { planFormativoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.planFormativo.detail.title">PlanFormativo</Translate> [<b>{planFormativoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.planFormativo.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{planFormativoEntity.descripcion}</dd>
          <dt>
            <span id="descripcionLarga">
              <Translate contentKey="experisFormacionApp.planFormativo.descripcionLarga">Descripcion Larga</Translate>
            </span>
          </dt>
          <dd>{planFormativoEntity.descripcionLarga}</dd>
          <dt>
            <span id="fechaInicio">
              <Translate contentKey="experisFormacionApp.planFormativo.fechaInicio">Fecha Inicio</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={planFormativoEntity.fechaInicio} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="fechaFin">
              <Translate contentKey="experisFormacionApp.planFormativo.fechaFin">Fecha Fin</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={planFormativoEntity.fechaFin} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
        </dl>
        <Button tag={Link} to="/plan-formativo" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/plan-formativo/${planFormativoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ planFormativo }: IRootState) => ({
  planFormativoEntity: planFormativo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlanFormativoDetail);
