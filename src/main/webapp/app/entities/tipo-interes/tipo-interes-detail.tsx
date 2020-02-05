import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tipo-interes.reducer';
import { ITipoInteres } from 'app/shared/model/tipo-interes.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipoInteresDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TipoInteresDetail = (props: ITipoInteresDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tipoInteresEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.tipoInteres.detail.title">TipoInteres</Translate> [<b>{tipoInteresEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.tipoInteres.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{tipoInteresEntity.descripcion}</dd>
          <dt>
            <span id="descripcionLarga">
              <Translate contentKey="experisFormacionApp.tipoInteres.descripcionLarga">Descripcion Larga</Translate>
            </span>
          </dt>
          <dd>{tipoInteresEntity.descripcionLarga}</dd>
        </dl>
        <Button tag={Link} to="/tipo-interes" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tipo-interes/${tipoInteresEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tipoInteres }: IRootState) => ({
  tipoInteresEntity: tipoInteres.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TipoInteresDetail);
