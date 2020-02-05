import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './curso.reducer';
import { ICurso } from 'app/shared/model/curso.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICursoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursoUpdate = (props: ICursoUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cursoEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/curso' + props.location.search);
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
    values.hora = convertDateTimeToServer(values.hora);

    if (errors.length === 0) {
      const entity = {
        ...cursoEntity,
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
          <h2 id="experisFormacionApp.curso.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.curso.home.createOrEditLabel">Create or edit a Curso</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cursoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="curso-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="curso-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descripcionLabel" for="curso-descripcion">
                  <Translate contentKey="experisFormacionApp.curso.descripcion">Descripcion</Translate>
                </Label>
                <AvField
                  id="curso-descripcion"
                  type="text"
                  name="descripcion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descripcionLargaLabel" for="curso-descripcionLarga">
                  <Translate contentKey="experisFormacionApp.curso.descripcionLarga">Descripcion Larga</Translate>
                </Label>
                <AvField id="curso-descripcionLarga" type="text" name="descripcionLarga" />
              </AvGroup>
              <AvGroup>
                <Label id="fechaInicioLabel" for="curso-fechaInicio">
                  <Translate contentKey="experisFormacionApp.curso.fechaInicio">Fecha Inicio</Translate>
                </Label>
                <AvField
                  id="curso-fechaInicio"
                  type="date"
                  className="form-control"
                  name="fechaInicio"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fechaFinLabel" for="curso-fechaFin">
                  <Translate contentKey="experisFormacionApp.curso.fechaFin">Fecha Fin</Translate>
                </Label>
                <AvField
                  id="curso-fechaFin"
                  type="date"
                  className="form-control"
                  name="fechaFin"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="telematicoPresencialLabel" for="curso-telematicoPresencial">
                  <Translate contentKey="experisFormacionApp.curso.telematicoPresencial">Telematico Presencial</Translate>
                </Label>
                <AvField
                  id="curso-telematicoPresencial"
                  type="text"
                  name="telematicoPresencial"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    pattern: { value: '^(T|P|t|p)$', errorMessage: translate('entity.validation.pattern', { pattern: '^(T|P|t|p)$' }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="horaLabel" for="curso-hora">
                  <Translate contentKey="experisFormacionApp.curso.hora">Hora</Translate>
                </Label>
                <AvInput
                  id="curso-hora"
                  type="datetime-local"
                  className="form-control"
                  name="hora"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? null : convertDateTimeFromServer(props.cursoEntity.hora)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="ubicacionLabel" for="curso-ubicacion">
                  <Translate contentKey="experisFormacionApp.curso.ubicacion">Ubicacion</Translate>
                </Label>
                <AvField id="curso-ubicacion" type="text" name="ubicacion" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroHorasLabel" for="curso-numeroHoras">
                  <Translate contentKey="experisFormacionApp.curso.numeroHoras">Numero Horas</Translate>
                </Label>
                <AvField id="curso-numeroHoras" type="string" className="form-control" name="numeroHoras" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/curso" replace color="info">
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
  cursoEntity: storeState.curso.entity,
  loading: storeState.curso.loading,
  updating: storeState.curso.updating,
  updateSuccess: storeState.curso.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoUpdate);
