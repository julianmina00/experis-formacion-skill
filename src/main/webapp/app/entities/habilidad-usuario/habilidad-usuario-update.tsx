import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHabilidad } from 'app/shared/model/habilidad.model';
import { getEntities as getHabilidads } from 'app/entities/habilidad/habilidad.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { getEntity, updateEntity, createEntity, reset } from './habilidad-usuario.reducer';
import { IHabilidadUsuario } from 'app/shared/model/habilidad-usuario.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHabilidadUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HabilidadUsuarioUpdate = (props: IHabilidadUsuarioUpdateProps) => {
  const [habilidadId, setHabilidadId] = useState('0');
  const [usuarioId, setUsuarioId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { habilidadUsuarioEntity, habilidads, usuarios, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/habilidad-usuario');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getHabilidads();
    props.getUsuarios();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...habilidadUsuarioEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="experisFormacionApp.habilidadUsuario.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.habilidadUsuario.home.createOrEditLabel">
              Create or edit a HabilidadUsuario
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : habilidadUsuarioEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="habilidad-usuario-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="habilidad-usuario-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="habilidad-usuario-habilidad">
                  <Translate contentKey="experisFormacionApp.habilidadUsuario.habilidad">Habilidad</Translate>
                </Label>
                <AvInput id="habilidad-usuario-habilidad" type="select" className="form-control" name="habilidadId">
                  <option value="" key="0" />
                  {habilidads
                    ? habilidads.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="habilidad-usuario-usuario">
                  <Translate contentKey="experisFormacionApp.habilidadUsuario.usuario">Usuario</Translate>
                </Label>
                <AvInput id="habilidad-usuario-usuario" type="select" className="form-control" name="usuarioId">
                  <option value="" key="0" />
                  {usuarios
                    ? usuarios.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/habilidad-usuario" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  habilidads: storeState.habilidad.entities,
  usuarios: storeState.usuario.entities,
  habilidadUsuarioEntity: storeState.habilidadUsuario.entity,
  loading: storeState.habilidadUsuario.loading,
  updating: storeState.habilidadUsuario.updating,
  updateSuccess: storeState.habilidadUsuario.updateSuccess
});

const mapDispatchToProps = {
  getHabilidads,
  getUsuarios,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HabilidadUsuarioUpdate);
