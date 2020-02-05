import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './habilidad.reducer';
import { IHabilidad } from 'app/shared/model/habilidad.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHabilidadDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HabilidadDetail = (props: IHabilidadDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { habilidadEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.habilidad.detail.title">Habilidad</Translate> [<b>{habilidadEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.habilidad.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{habilidadEntity.descripcion}</dd>
          <dt>
            <span id="descripcionLarga">
              <Translate contentKey="experisFormacionApp.habilidad.descripcionLarga">Descripcion Larga</Translate>
            </span>
          </dt>
          <dd>{habilidadEntity.descripcionLarga}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.habilidad.tipoHabilidad">Tipo Habilidad</Translate>
          </dt>
          <dd>{habilidadEntity.tipoHabilidadId ? habilidadEntity.tipoHabilidadId : ''}</dd>
        </dl>
        <Button tag={Link} to="/habilidad" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/habilidad/${habilidadEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ habilidad }: IRootState) => ({
  habilidadEntity: habilidad.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HabilidadDetail);
