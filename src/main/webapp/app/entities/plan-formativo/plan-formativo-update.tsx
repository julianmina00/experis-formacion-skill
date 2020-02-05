import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './plan-formativo.reducer';
import { IPlanFormativo } from 'app/shared/model/plan-formativo.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPlanFormativoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlanFormativoUpdate = (props: IPlanFormativoUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { planFormativoEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/plan-formativo' + props.location.search);
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
        ...planFormativoEntity,
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
          <h2 id="experisFormacionApp.planFormativo.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.planFormativo.home.createOrEditLabel">Create or edit a PlanFormativo</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : planFormativoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="plan-formativo-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="plan-formativo-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descripcionLabel" for="plan-formativo-descripcion">
                  <Translate contentKey="experisFormacionApp.planFormativo.descripcion">Descripcion</Translate>
                </Label>
                <AvField
                  id="plan-formativo-descripcion"
                  type="text"
                  name="descripcion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descripcionLargaLabel" for="plan-formativo-descripcionLarga">
                  <Translate contentKey="experisFormacionApp.planFormativo.descripcionLarga">Descripcion Larga</Translate>
                </Label>
                <AvField id="plan-formativo-descripcionLarga" type="text" name="descripcionLarga" />
              </AvGroup>
              <AvGroup>
                <Label id="fechaInicioLabel" for="plan-formativo-fechaInicio">
                  <Translate contentKey="experisFormacionApp.planFormativo.fechaInicio">Fecha Inicio</Translate>
                </Label>
                <AvField
                  id="plan-formativo-fechaInicio"
                  type="date"
                  className="form-control"
                  name="fechaInicio"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fechaFinLabel" for="plan-formativo-fechaFin">
                  <Translate contentKey="experisFormacionApp.planFormativo.fechaFin">Fecha Fin</Translate>
                </Label>
                <AvField
                  id="plan-formativo-fechaFin"
                  type="date"
                  className="form-control"
                  name="fechaFin"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/plan-formativo" replace color="info">
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
  planFormativoEntity: storeState.planFormativo.entity,
  loading: storeState.planFormativo.loading,
  updating: storeState.planFormativo.updating,
  updateSuccess: storeState.planFormativo.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlanFormativoUpdate);
