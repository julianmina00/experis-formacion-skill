import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './idioma-usuario.reducer';
import { IIdiomaUsuario } from 'app/shared/model/idioma-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIdiomaUsuarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IdiomaUsuarioDetail = (props: IIdiomaUsuarioDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { idiomaUsuarioEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.idiomaUsuario.detail.title">IdiomaUsuario</Translate> [<b>{idiomaUsuarioEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="idioma">
              <Translate contentKey="experisFormacionApp.idiomaUsuario.idioma">Idioma</Translate>
            </span>
          </dt>
          <dd>{idiomaUsuarioEntity.idioma}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.idiomaUsuario.usuario">Usuario</Translate>
          </dt>
          <dd>{idiomaUsuarioEntity.usuarioId ? idiomaUsuarioEntity.usuarioId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.idiomaUsuario.nivelIdioma">Nivel Idioma</Translate>
          </dt>
          <dd>{idiomaUsuarioEntity.nivelIdiomaId ? idiomaUsuarioEntity.nivelIdiomaId : ''}</dd>
        </dl>
        <Button tag={Link} to="/idioma-usuario" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/idioma-usuario/${idiomaUsuarioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ idiomaUsuario }: IRootState) => ({
  idiomaUsuarioEntity: idiomaUsuario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IdiomaUsuarioDetail);
