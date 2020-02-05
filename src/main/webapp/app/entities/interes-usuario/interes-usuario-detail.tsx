import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './interes-usuario.reducer';
import { IInteresUsuario } from 'app/shared/model/interes-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInteresUsuarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteresUsuarioDetail = (props: IInteresUsuarioDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { interesUsuarioEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.interesUsuario.detail.title">InteresUsuario</Translate> [
          <b>{interesUsuarioEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <Translate contentKey="experisFormacionApp.interesUsuario.interes">Interes</Translate>
          </dt>
          <dd>{interesUsuarioEntity.interesId ? interesUsuarioEntity.interesId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.interesUsuario.usuario">Usuario</Translate>
          </dt>
          <dd>{interesUsuarioEntity.usuarioId ? interesUsuarioEntity.usuarioId : ''}</dd>
        </dl>
        <Button tag={Link} to="/interes-usuario" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/interes-usuario/${interesUsuarioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ interesUsuario }: IRootState) => ({
  interesUsuarioEntity: interesUsuario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteresUsuarioDetail);
