import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICurso } from 'app/shared/model/curso.model';
import { getEntities as getCursos } from 'app/entities/curso/curso.reducer';
import { IInteres } from 'app/shared/model/interes.model';
import { getEntities as getInteres } from 'app/entities/interes/interes.reducer';
import { IHabilidad } from 'app/shared/model/habilidad.model';
import { getEntities as getHabilidads } from 'app/entities/habilidad/habilidad.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contenido-curso.reducer';
import { IContenidoCurso } from 'app/shared/model/contenido-curso.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IContenidoCursoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContenidoCursoUpdate = (props: IContenidoCursoUpdateProps) => {
  const [cursoId, setCursoId] = useState('0');
  const [interesId, setInteresId] = useState('0');
  const [habilidadId, setHabilidadId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { contenidoCursoEntity, cursos, interes, habilidads, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/contenido-curso' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCursos();
    props.getInteres();
    props.getHabilidads();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...contenidoCursoEntity,
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
          <h2 id="experisFormacionApp.contenidoCurso.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.contenidoCurso.home.createOrEditLabel">Create or edit a ContenidoCurso</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : contenidoCursoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="contenido-curso-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="contenido-curso-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="habilidadInteresLabel" for="contenido-curso-habilidadInteres">
                  <Translate contentKey="experisFormacionApp.contenidoCurso.habilidadInteres">Habilidad Interes</Translate>
                </Label>
                <AvField
                  id="contenido-curso-habilidadInteres"
                  type="text"
                  name="habilidadInteres"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    pattern: { value: '^(H|I|h|i)$', errorMessage: translate('entity.validation.pattern', { pattern: '^(H|I|h|i)$' }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="contenido-curso-curso">
                  <Translate contentKey="experisFormacionApp.contenidoCurso.curso">Curso</Translate>
                </Label>
                <AvInput id="contenido-curso-curso" type="select" className="form-control" name="cursoId">
                  <option value="" key="0" />
                  {cursos
                    ? cursos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="contenido-curso-interes">
                  <Translate contentKey="experisFormacionApp.contenidoCurso.interes">Interes</Translate>
                </Label>
                <AvInput id="contenido-curso-interes" type="select" className="form-control" name="interesId">
                  <option value="" key="0" />
                  {interes
                    ? interes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="contenido-curso-habilidad">
                  <Translate contentKey="experisFormacionApp.contenidoCurso.habilidad">Habilidad</Translate>
                </Label>
                <AvInput id="contenido-curso-habilidad" type="select" className="form-control" name="habilidadId">
                  <option value="" key="0" />
                  {habilidads
                    ? habilidads.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/contenido-curso" replace color="info">
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
  cursos: storeState.curso.entities,
  interes: storeState.interes.entities,
  habilidads: storeState.habilidad.entities,
  contenidoCursoEntity: storeState.contenidoCurso.entity,
  loading: storeState.contenidoCurso.loading,
  updating: storeState.contenidoCurso.updating,
  updateSuccess: storeState.contenidoCurso.updateSuccess
});

const mapDispatchToProps = {
  getCursos,
  getInteres,
  getHabilidads,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContenidoCursoUpdate);
