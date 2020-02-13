import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './usuario.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UsuarioUpdate = (props: IUsuarioUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { usuarioEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/usuario' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...usuarioEntity,
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
          <h2 id="experisFormacionApp.usuario.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.usuario.home.createOrEditLabel">Create or edit a Usuario</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : usuarioEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="usuario-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="usuario-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="documentoLabel" for="usuario-documento">
                  <Translate contentKey="experisFormacionApp.usuario.documento">Documento</Translate>
                </Label>
                <AvField
                  id="usuario-documento"
                  type="text"
                  name="documento"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="tipoDocumentoLabel" for="usuario-tipoDocumento">
                  <Translate contentKey="experisFormacionApp.usuario.tipoDocumento">Tipo Documento</Translate>
                </Label>
                <AvField
                  id="usuario-tipoDocumento"
                  type="text"
                  name="tipoDocumento"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nombreLabel" for="usuario-nombre">
                  <Translate contentKey="experisFormacionApp.usuario.nombre">Nombre</Translate>
                </Label>
                <AvField
                  id="usuario-nombre"
                  type="text"
                  name="nombre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="apellidosLabel" for="usuario-apellidos">
                  <Translate contentKey="experisFormacionApp.usuario.apellidos">Apellidos</Translate>
                </Label>
                <AvField
                  id="usuario-apellidos"
                  type="text"
                  name="apellidos"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="usuario-email">
                  <Translate contentKey="experisFormacionApp.usuario.email">Email</Translate>
                </Label>
                <AvField
                  id="usuario-email"
                  type="text"
                  name="email"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    pattern: {
                      value: '^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$',
                      errorMessage: translate('entity.validation.pattern', { pattern: '^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$' })
                    }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="telefonoLabel" for="usuario-telefono">
                  <Translate contentKey="experisFormacionApp.usuario.telefono">Telefono</Translate>
                </Label>
                <AvField id="usuario-telefono" type="text" name="telefono" />
              </AvGroup>
              <AvGroup>
                <Label id="rolLabel" for="usuario-rol">
                  <Translate contentKey="experisFormacionApp.usuario.rol">Rol</Translate>
                </Label>
                <AvField
                  id="usuario-rol"
                  type="text"
                  name="rol"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="proyectoLabel" for="usuario-proyecto">
                  <Translate contentKey="experisFormacionApp.usuario.proyecto">Proyecto</Translate>
                </Label>
                <AvField id="usuario-proyecto" type="text" name="proyecto" />
              </AvGroup>
              <AvGroup>
                <Label id="companiaLabel" for="usuario-compania">
                  <Translate contentKey="experisFormacionApp.usuario.compania">Compania</Translate>
                </Label>
                <AvField id="usuario-compania" type="text" name="compania" />
              </AvGroup>
              <AvGroup>
                <Label id="ubicacionLabel" for="usuario-ubicacion">
                  <Translate contentKey="experisFormacionApp.usuario.ubicacion">Ubicacion</Translate>
                </Label>
                <AvField id="usuario-ubicacion" type="text" name="ubicacion" />
              </AvGroup>
              <AvGroup>
                <Label id="managerNombreLabel" for="usuario-managerNombre">
                  <Translate contentKey="experisFormacionApp.usuario.managerNombre">Manager Nombre</Translate>
                </Label>
                <AvField
                  id="usuario-managerNombre"
                  type="text"
                  name="managerNombre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="managerEmailLabel" for="usuario-managerEmail">
                  <Translate contentKey="experisFormacionApp.usuario.managerEmail">Manager Email</Translate>
                </Label>
                <AvField id="usuario-managerEmail" type="text" name="managerEmail" />
              </AvGroup>
              <AvGroup>
                <Label id="talentMentorNombreLabel" for="usuario-talentMentorNombre">
                  <Translate contentKey="experisFormacionApp.usuario.talentMentorNombre">Talent Mentor Nombre</Translate>
                </Label>
                <AvField id="usuario-talentMentorNombre" type="text" name="talentMentorNombre" />
              </AvGroup>
              <AvGroup>
                <Label id="talentMentorEmailLabel" for="usuario-talentMentorEmail">
                  <Translate contentKey="experisFormacionApp.usuario.talentMentorEmail">Talent Mentor Email</Translate>
                </Label>
                <AvField id="usuario-talentMentorEmail" type="text" name="talentMentorEmail" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/usuario" replace color="info">
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
  usuarioEntity: storeState.usuario.entity,
  loading: storeState.usuario.loading,
  updating: storeState.usuario.updating,
  updateSuccess: storeState.usuario.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UsuarioUpdate);
