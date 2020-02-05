import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICurso } from 'app/shared/model/curso.model';
import { getEntities as getCursos } from 'app/entities/curso/curso.reducer';
import { IPlanFormativo } from 'app/shared/model/plan-formativo.model';
import { getEntities as getPlanFormativos } from 'app/entities/plan-formativo/plan-formativo.reducer';
import { getEntity, updateEntity, createEntity, reset } from './curso-plan-formativo.reducer';
import { ICursoPlanFormativo } from 'app/shared/model/curso-plan-formativo.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICursoPlanFormativoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursoPlanFormativoUpdate = (props: ICursoPlanFormativoUpdateProps) => {
  const [cursoId, setCursoId] = useState('0');
  const [planFormativoId, setPlanFormativoId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cursoPlanFormativoEntity, cursos, planFormativos, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/curso-plan-formativo');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCursos();
    props.getPlanFormativos();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...cursoPlanFormativoEntity,
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
          <h2 id="experisFormacionApp.cursoPlanFormativo.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.cursoPlanFormativo.home.createOrEditLabel">
              Create or edit a CursoPlanFormativo
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cursoPlanFormativoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="curso-plan-formativo-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="curso-plan-formativo-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="curso-plan-formativo-curso">
                  <Translate contentKey="experisFormacionApp.cursoPlanFormativo.curso">Curso</Translate>
                </Label>
                <AvInput id="curso-plan-formativo-curso" type="select" className="form-control" name="cursoId">
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
                <Label for="curso-plan-formativo-planFormativo">
                  <Translate contentKey="experisFormacionApp.cursoPlanFormativo.planFormativo">Plan Formativo</Translate>
                </Label>
                <AvInput id="curso-plan-formativo-planFormativo" type="select" className="form-control" name="planFormativoId">
                  <option value="" key="0" />
                  {planFormativos
                    ? planFormativos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/curso-plan-formativo" replace color="info">
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
  planFormativos: storeState.planFormativo.entities,
  cursoPlanFormativoEntity: storeState.cursoPlanFormativo.entity,
  loading: storeState.cursoPlanFormativo.loading,
  updating: storeState.cursoPlanFormativo.updating,
  updateSuccess: storeState.cursoPlanFormativo.updateSuccess
});

const mapDispatchToProps = {
  getCursos,
  getPlanFormativos,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoPlanFormativoUpdate);
