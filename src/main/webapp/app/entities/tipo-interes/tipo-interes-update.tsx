import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tipo-interes.reducer';
import { ITipoInteres } from 'app/shared/model/tipo-interes.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITipoInteresUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TipoInteresUpdate = (props: ITipoInteresUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tipoInteresEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tipo-interes' + props.location.search);
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
        ...tipoInteresEntity,
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
          <h2 id="experisFormacionApp.tipoInteres.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.tipoInteres.home.createOrEditLabel">Create or edit a TipoInteres</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tipoInteresEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tipo-interes-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tipo-interes-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descripcionLabel" for="tipo-interes-descripcion">
                  <Translate contentKey="experisFormacionApp.tipoInteres.descripcion">Descripcion</Translate>
                </Label>
                <AvField
                  id="tipo-interes-descripcion"
                  type="text"
                  name="descripcion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descripcionLargaLabel" for="tipo-interes-descripcionLarga">
                  <Translate contentKey="experisFormacionApp.tipoInteres.descripcionLarga">Descripcion Larga</Translate>
                </Label>
                <AvField id="tipo-interes-descripcionLarga" type="text" name="descripcionLarga" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tipo-interes" replace color="info">
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
  tipoInteresEntity: storeState.tipoInteres.entity,
  loading: storeState.tipoInteres.loading,
  updating: storeState.tipoInteres.updating,
  updateSuccess: storeState.tipoInteres.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TipoInteresUpdate);
