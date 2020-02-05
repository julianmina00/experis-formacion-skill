import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IInteres } from 'app/shared/model/interes.model';
import { getEntities as getInteres } from 'app/entities/interes/interes.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { getEntity, updateEntity, createEntity, reset } from './interes-usuario.reducer';
import { IInteresUsuario } from 'app/shared/model/interes-usuario.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInteresUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteresUsuarioUpdate = (props: IInteresUsuarioUpdateProps) => {
  const [interesId, setInteresId] = useState('0');
  const [usuarioId, setUsuarioId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { interesUsuarioEntity, interes, usuarios, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/interes-usuario');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getInteres();
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
        ...interesUsuarioEntity,
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
          <h2 id="experisFormacionApp.interesUsuario.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.interesUsuario.home.createOrEditLabel">Create or edit a InteresUsuario</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : interesUsuarioEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="interes-usuario-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="interes-usuario-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="interes-usuario-interes">
                  <Translate contentKey="experisFormacionApp.interesUsuario.interes">Interes</Translate>
                </Label>
                <AvInput id="interes-usuario-interes" type="select" className="form-control" name="interesId">
                  <option value="" key="0" />
                  {interes
                    ? interes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="interes-usuario-usuario">
                  <Translate contentKey="experisFormacionApp.interesUsuario.usuario">Usuario</Translate>
                </Label>
                <AvInput id="interes-usuario-usuario" type="select" className="form-control" name="usuarioId">
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
              <Button tag={Link} id="cancel-save" to="/interes-usuario" replace color="info">
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
  interes: storeState.interes.entities,
  usuarios: storeState.usuario.entities,
  interesUsuarioEntity: storeState.interesUsuario.entity,
  loading: storeState.interesUsuario.loading,
  updating: storeState.interesUsuario.updating,
  updateSuccess: storeState.interesUsuario.updateSuccess
});

const mapDispatchToProps = {
  getInteres,
  getUsuarios,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteresUsuarioUpdate);
