import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './usuario.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUsuarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UsuarioDetail = (props: IUsuarioDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { usuarioEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.usuario.detail.title">Usuario</Translate> [<b>{usuarioEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="documento">
              <Translate contentKey="experisFormacionApp.usuario.documento">Documento</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.documento}</dd>
          <dt>
            <span id="tipoDocumento">
              <Translate contentKey="experisFormacionApp.usuario.tipoDocumento">Tipo Documento</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.tipoDocumento}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="experisFormacionApp.usuario.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.nombre}</dd>
          <dt>
            <span id="apellidos">
              <Translate contentKey="experisFormacionApp.usuario.apellidos">Apellidos</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.apellidos}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="experisFormacionApp.usuario.email">Email</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.email}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="experisFormacionApp.usuario.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.telefono}</dd>
          <dt>
            <span id="rol">
              <Translate contentKey="experisFormacionApp.usuario.rol">Rol</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.rol}</dd>
          <dt>
            <span id="proyecto">
              <Translate contentKey="experisFormacionApp.usuario.proyecto">Proyecto</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.proyecto}</dd>
          <dt>
            <span id="ubicacion">
              <Translate contentKey="experisFormacionApp.usuario.ubicacion">Ubicacion</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.ubicacion}</dd>
          <dt>
            <span id="managerNombre">
              <Translate contentKey="experisFormacionApp.usuario.managerNombre">Manager Nombre</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.managerNombre}</dd>
          <dt>
            <span id="managerEmail">
              <Translate contentKey="experisFormacionApp.usuario.managerEmail">Manager Email</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.managerEmail}</dd>
          <dt>
            <span id="talentMentorNombre">
              <Translate contentKey="experisFormacionApp.usuario.talentMentorNombre">Talent Mentor Nombre</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.talentMentorNombre}</dd>
          <dt>
            <span id="talentMentorEmail">
              <Translate contentKey="experisFormacionApp.usuario.talentMentorEmail">Talent Mentor Email</Translate>
            </span>
          </dt>
          <dd>{usuarioEntity.talentMentorEmail}</dd>
        </dl>
        <Button tag={Link} to="/usuario" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/usuario/${usuarioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ usuario }: IRootState) => ({
  usuarioEntity: usuario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UsuarioDetail);
