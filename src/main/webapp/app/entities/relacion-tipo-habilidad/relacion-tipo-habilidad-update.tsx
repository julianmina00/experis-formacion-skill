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
import { getEntity, updateEntity, createEntity, reset } from './relacion-tipo-habilidad.reducer';
import { IRelacionTipoHabilidad } from 'app/shared/model/relacion-tipo-habilidad.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRelacionTipoHabilidadUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelacionTipoHabilidadUpdate = (props: IRelacionTipoHabilidadUpdateProps) => {
  const [padreId, setPadreId] = useState('0');
  const [hijaId, setHijaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { relacionTipoHabilidadEntity, tipoHabilidads, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/relacion-tipo-habilidad' + props.location.search);
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
        ...relacionTipoHabilidadEntity,
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
          <h2 id="experisFormacionApp.relacionTipoHabilidad.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.home.createOrEditLabel">
              Create or edit a RelacionTipoHabilidad
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : relacionTipoHabilidadEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="relacion-tipo-habilidad-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="relacion-tipo-habilidad-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="profundidadLabel" for="relacion-tipo-habilidad-profundidad">
                  <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.profundidad">Profundidad</Translate>
                </Label>
                <AvField
                  id="relacion-tipo-habilidad-profundidad"
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
                <Label for="relacion-tipo-habilidad-padre">
                  <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.padre">Padre</Translate>
                </Label>
                <AvInput id="relacion-tipo-habilidad-padre" type="select" className="form-control" name="padreId">
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
              <AvGroup>
                <Label for="relacion-tipo-habilidad-hija">
                  <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.hija">Hija</Translate>
                </Label>
                <AvInput id="relacion-tipo-habilidad-hija" type="select" className="form-control" name="hijaId">
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
              <Button tag={Link} id="cancel-save" to="/relacion-tipo-habilidad" replace color="info">
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
  relacionTipoHabilidadEntity: storeState.relacionTipoHabilidad.entity,
  loading: storeState.relacionTipoHabilidad.loading,
  updating: storeState.relacionTipoHabilidad.updating,
  updateSuccess: storeState.relacionTipoHabilidad.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(RelacionTipoHabilidadUpdate);
