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
import { getEntity, updateEntity, createEntity, reset } from './relacion-tipo-interes.reducer';
import { IRelacionTipoInteres } from 'app/shared/model/relacion-tipo-interes.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRelacionTipoInteresUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelacionTipoInteresUpdate = (props: IRelacionTipoInteresUpdateProps) => {
  const [padreId, setPadreId] = useState('0');
  const [hijaId, setHijaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { relacionTipoInteresEntity, tipoInteres, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/relacion-tipo-interes' + props.location.search);
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
        ...relacionTipoInteresEntity,
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
          <h2 id="experisFormacionApp.relacionTipoInteres.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.relacionTipoInteres.home.createOrEditLabel">
              Create or edit a RelacionTipoInteres
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : relacionTipoInteresEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="relacion-tipo-interes-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="relacion-tipo-interes-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="profundidadLabel" for="relacion-tipo-interes-profundidad">
                  <Translate contentKey="experisFormacionApp.relacionTipoInteres.profundidad">Profundidad</Translate>
                </Label>
                <AvField
                  id="relacion-tipo-interes-profundidad"
                  type="string"
                  className="form-control"
                  name="profundidad"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="relacion-tipo-interes-padre">
                  <Translate contentKey="experisFormacionApp.relacionTipoInteres.padre">Padre</Translate>
                </Label>
                <AvInput id="relacion-tipo-interes-padre" type="select" className="form-control" name="padreId">
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
              <AvGroup>
                <Label for="relacion-tipo-interes-hija">
                  <Translate contentKey="experisFormacionApp.relacionTipoInteres.hija">Hija</Translate>
                </Label>
                <AvInput id="relacion-tipo-interes-hija" type="select" className="form-control" name="hijaId">
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
              <Button tag={Link} id="cancel-save" to="/relacion-tipo-interes" replace color="info">
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
  relacionTipoInteresEntity: storeState.relacionTipoInteres.entity,
  loading: storeState.relacionTipoInteres.loading,
  updating: storeState.relacionTipoInteres.updating,
  updateSuccess: storeState.relacionTipoInteres.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(RelacionTipoInteresUpdate);
