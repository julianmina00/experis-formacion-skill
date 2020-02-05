import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './interes.reducer';
import { IInteres } from 'app/shared/model/interes.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInteresDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteresDetail = (props: IInteresDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { interesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.interes.detail.title">Interes</Translate> [<b>{interesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.interes.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{interesEntity.descripcion}</dd>
          <dt>
            <span id="descripcionLarga">
              <Translate contentKey="experisFormacionApp.interes.descripcionLarga">Descripcion Larga</Translate>
            </span>
          </dt>
          <dd>{interesEntity.descripcionLarga}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.interes.tipoInteres">Tipo Interes</Translate>
          </dt>
          <dd>{interesEntity.tipoInteresId ? interesEntity.tipoInteresId : ''}</dd>
        </dl>
        <Button tag={Link} to="/interes" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/interes/${interesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ interes }: IRootState) => ({
  interesEntity: interes.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteresDetail);
