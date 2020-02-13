import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUsuario } from 'app/shared/model/usuario.model';
import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { INivelIdioma } from 'app/shared/model/nivel-idioma.model';
import { getEntities as getNivelIdiomas } from 'app/entities/nivel-idioma/nivel-idioma.reducer';
import { getEntity, updateEntity, createEntity, reset } from './idioma-usuario.reducer';
import { IIdiomaUsuario } from 'app/shared/model/idioma-usuario.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIdiomaUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IdiomaUsuarioUpdate = (props: IIdiomaUsuarioUpdateProps) => {
  const [usuarioId, setUsuarioId] = useState('0');
  const [nivelIdiomaId, setNivelIdiomaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { idiomaUsuarioEntity, usuarios, nivelIdiomas, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/idioma-usuario' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getUsuarios();
    props.getNivelIdiomas();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...idiomaUsuarioEntity,
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
          <h2 id="experisFormacionApp.idiomaUsuario.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.idiomaUsuario.home.createOrEditLabel">Create or edit a IdiomaUsuario</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : idiomaUsuarioEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="idioma-usuario-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="idioma-usuario-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idiomaLabel" for="idioma-usuario-idioma">
                  <Translate contentKey="experisFormacionApp.idiomaUsuario.idioma">Idioma</Translate>
                </Label>
                <AvField
                  id="idioma-usuario-idioma"
                  type="text"
                  name="idioma"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="idioma-usuario-usuario">
                  <Translate contentKey="experisFormacionApp.idiomaUsuario.usuario">Usuario</Translate>
                </Label>
                <AvInput id="idioma-usuario-usuario" type="select" className="form-control" name="usuarioId">
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
              <AvGroup>
                <Label for="idioma-usuario-nivelIdioma">
                  <Translate contentKey="experisFormacionApp.idiomaUsuario.nivelIdioma">Nivel Idioma</Translate>
                </Label>
                <AvInput id="idioma-usuario-nivelIdioma" type="select" className="form-control" name="nivelIdiomaId">
                  <option value="" key="0" />
                  {nivelIdiomas
                    ? nivelIdiomas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/idioma-usuario" replace color="info">
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
  usuarios: storeState.usuario.entities,
  nivelIdiomas: storeState.nivelIdioma.entities,
  idiomaUsuarioEntity: storeState.idiomaUsuario.entity,
  loading: storeState.idiomaUsuario.loading,
  updating: storeState.idiomaUsuario.updating,
  updateSuccess: storeState.idiomaUsuario.updateSuccess
});

const mapDispatchToProps = {
  getUsuarios,
  getNivelIdiomas,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IdiomaUsuarioUpdate);
