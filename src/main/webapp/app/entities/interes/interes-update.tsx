import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITipoInteres } from 'app/shared/model/tipo-interes.model';
import { getEntities as getTipoInteres } from 'app/entities/tipo-interes/tipo-interes.reducer';
import { getEntity, updateEntity, createEntity, reset } from './interes.reducer';
import { IInteres } from 'app/shared/model/interes.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInteresUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteresUpdate = (props: IInteresUpdateProps) => {
  const [tipoInteresId, setTipoInteresId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { interesEntity, tipoInteres, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/interes' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTipoInteres();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...interesEntity,
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
          <h2 id="experisFormacionApp.interes.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.interes.home.createOrEditLabel">Create or edit a Interes</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : interesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="interes-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="interes-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descripcionLabel" for="interes-descripcion">
                  <Translate contentKey="experisFormacionApp.interes.descripcion">Descripcion</Translate>
                </Label>
                <AvField
                  id="interes-descripcion"
                  type="text"
                  name="descripcion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descripcionLargaLabel" for="interes-descripcionLarga">
                  <Translate contentKey="experisFormacionApp.interes.descripcionLarga">Descripcion Larga</Translate>
                </Label>
                <AvField id="interes-descripcionLarga" type="text" name="descripcionLarga" />
              </AvGroup>
              <AvGroup>
                <Label for="interes-tipoInteres">
                  <Translate contentKey="experisFormacionApp.interes.tipoInteres">Tipo Interes</Translate>
                </Label>
                <AvInput id="interes-tipoInteres" type="select" className="form-control" name="tipoInteresId">
                  <option value="" key="0" />
                  {tipoInteres
                    ? tipoInteres.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/interes" replace color="info">
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
  tipoInteres: storeState.tipoInteres.entities,
  interesEntity: storeState.interes.entity,
  loading: storeState.interes.loading,
  updating: storeState.interes.updating,
  updateSuccess: storeState.interes.updateSuccess
});

const mapDispatchToProps = {
  getTipoInteres,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteresUpdate);
