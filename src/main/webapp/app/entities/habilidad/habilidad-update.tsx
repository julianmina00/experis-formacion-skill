import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITipoHabilidad } from 'app/shared/model/tipo-habilidad.model';
import { getEntities as getTipoHabilidads } from 'app/entities/tipo-habilidad/tipo-habilidad.reducer';
import { getEntity, updateEntity, createEntity, reset } from './habilidad.reducer';
import { IHabilidad } from 'app/shared/model/habilidad.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHabilidadUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HabilidadUpdate = (props: IHabilidadUpdateProps) => {
  const [tipoHabilidadId, setTipoHabilidadId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { habilidadEntity, tipoHabilidads, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/habilidad' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTipoHabilidads();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...habilidadEntity,
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
          <h2 id="experisFormacionApp.habilidad.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.habilidad.home.createOrEditLabel">Create or edit a Habilidad</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : habilidadEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="habilidad-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="habilidad-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descripcionLabel" for="habilidad-descripcion">
                  <Translate contentKey="experisFormacionApp.habilidad.descripcion">Descripcion</Translate>
                </Label>
                <AvField
                  id="habilidad-descripcion"
                  type="text"
                  name="descripcion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descripcionLargaLabel" for="habilidad-descripcionLarga">
                  <Translate contentKey="experisFormacionApp.habilidad.descripcionLarga">Descripcion Larga</Translate>
                </Label>
                <AvField id="habilidad-descripcionLarga" type="text" name="descripcionLarga" />
              </AvGroup>
              <AvGroup>
                <Label for="habilidad-tipoHabilidad">
                  <Translate contentKey="experisFormacionApp.habilidad.tipoHabilidad">Tipo Habilidad</Translate>
                </Label>
                <AvInput id="habilidad-tipoHabilidad" type="select" className="form-control" name="tipoHabilidadId">
                  <option value="" key="0" />
                  {tipoHabilidads
                    ? tipoHabilidads.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/habilidad" replace color="info">
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
  tipoHabilidads: storeState.tipoHabilidad.entities,
  habilidadEntity: storeState.habilidad.entity,
  loading: storeState.habilidad.loading,
  updating: storeState.habilidad.updating,
  updateSuccess: storeState.habilidad.updateSuccess
});

const mapDispatchToProps = {
  getTipoHabilidads,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HabilidadUpdate);
