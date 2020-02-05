import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './curso-plan-formativo.reducer';
import { ICursoPlanFormativo } from 'app/shared/model/curso-plan-formativo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICursoPlanFormativoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursoPlanFormativoDetail = (props: ICursoPlanFormativoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cursoPlanFormativoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.cursoPlanFormativo.detail.title">CursoPlanFormativo</Translate> [
          <b>{cursoPlanFormativoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <Translate contentKey="experisFormacionApp.cursoPlanFormativo.curso">Curso</Translate>
          </dt>
          <dd>{cursoPlanFormativoEntity.cursoId ? cursoPlanFormativoEntity.cursoId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.cursoPlanFormativo.planFormativo">Plan Formativo</Translate>
          </dt>
          <dd>{cursoPlanFormativoEntity.planFormativoId ? cursoPlanFormativoEntity.planFormativoId : ''}</dd>
        </dl>
        <Button tag={Link} to="/curso-plan-formativo" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/curso-plan-formativo/${cursoPlanFormativoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cursoPlanFormativo }: IRootState) => ({
  cursoPlanFormativoEntity: cursoPlanFormativo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoPlanFormativoDetail);
