import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tipo-habilidad.reducer';
import { ITipoHabilidad } from 'app/shared/model/tipo-habilidad.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITipoHabilidadUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TipoHabilidadUpdate = (props: ITipoHabilidadUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tipoHabilidadEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tipo-habilidad' + props.location.search);
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
        ...tipoHabilidadEntity,
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
          <h2 id="experisFormacionApp.tipoHabilidad.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.tipoHabilidad.home.createOrEditLabel">Create or edit a TipoHabilidad</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tipoHabilidadEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tipo-habilidad-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tipo-habilidad-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descripcionLabel" for="tipo-habilidad-descripcion">
                  <Translate contentKey="experisFormacionApp.tipoHabilidad.descripcion">Descripcion</Translate>
                </Label>
                <AvField
                  id="tipo-habilidad-descripcion"
                  type="text"
                  name="descripcion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descripcionLargaLabel" for="tipo-habilidad-descripcionLarga">
                  <Translate contentKey="experisFormacionApp.tipoHabilidad.descripcionLarga">Descripcion Larga</Translate>
                </Label>
                <AvField id="tipo-habilidad-descripcionLarga" type="text" name="descripcionLarga" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tipo-habilidad" replace color="info">
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
  tipoHabilidadEntity: storeState.tipoHabilidad.entity,
  loading: storeState.tipoHabilidad.loading,
  updating: storeState.tipoHabilidad.updating,
  updateSuccess: storeState.tipoHabilidad.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TipoHabilidadUpdate);
