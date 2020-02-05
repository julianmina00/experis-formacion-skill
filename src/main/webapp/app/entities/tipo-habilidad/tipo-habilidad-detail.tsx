import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tipo-habilidad.reducer';
import { ITipoHabilidad } from 'app/shared/model/tipo-habilidad.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipoHabilidadDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TipoHabilidadDetail = (props: ITipoHabilidadDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tipoHabilidadEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.tipoHabilidad.detail.title">TipoHabilidad</Translate> [<b>{tipoHabilidadEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.tipoHabilidad.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{tipoHabilidadEntity.descripcion}</dd>
          <dt>
            <span id="descripcionLarga">
              <Translate contentKey="experisFormacionApp.tipoHabilidad.descripcionLarga">Descripcion Larga</Translate>
            </span>
          </dt>
          <dd>{tipoHabilidadEntity.descripcionLarga}</dd>
        </dl>
        <Button tag={Link} to="/tipo-habilidad" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tipo-habilidad/${tipoHabilidadEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tipoHabilidad }: IRootState) => ({
  tipoHabilidadEntity: tipoHabilidad.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TipoHabilidadDetail);
