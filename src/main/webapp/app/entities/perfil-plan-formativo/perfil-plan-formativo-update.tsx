import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHabilidad } from 'app/shared/model/habilidad.model';
import { getEntities as getHabilidads } from 'app/entities/habilidad/habilidad.reducer';
import { IInteres } from 'app/shared/model/interes.model';
import { getEntities as getInteres } from 'app/entities/interes/interes.reducer';
import { IPlanFormativo } from 'app/shared/model/plan-formativo.model';
import { getEntities as getPlanFormativos } from 'app/entities/plan-formativo/plan-formativo.reducer';
import { getEntity, updateEntity, createEntity, reset } from './perfil-plan-formativo.reducer';
import { IPerfilPlanFormativo } from 'app/shared/model/perfil-plan-formativo.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPerfilPlanFormativoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PerfilPlanFormativoUpdate = (props: IPerfilPlanFormativoUpdateProps) => {
  const [habilidadId, setHabilidadId] = useState('0');
  const [interesId, setInteresId] = useState('0');
  const [planFormativoId, setPlanFormativoId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { perfilPlanFormativoEntity, habilidads, interes, planFormativos, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/perfil-plan-formativo');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getHabilidads();
    props.getInteres();
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
        ...perfilPlanFormativoEntity,
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
          <h2 id="experisFormacionApp.perfilPlanFormativo.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.perfilPlanFormativo.home.createOrEditLabel">
              Create or edit a PerfilPlanFormativo
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : perfilPlanFormativoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="perfil-plan-formativo-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="perfil-plan-formativo-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="interesHabilidadLabel" for="perfil-plan-formativo-interesHabilidad">
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.interesHabilidad">Interes Habilidad</Translate>
                </Label>
                <AvField
                  id="perfil-plan-formativo-interesHabilidad"
                  type="text"
                  name="interesHabilidad"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    pattern: { value: '^(H|I|h|i)$', errorMessage: translate('entity.validation.pattern', { pattern: '^(H|I|h|i)$' }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="perfil-plan-formativo-habilidad">
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.habilidad">Habilidad</Translate>
                </Label>
                <AvInput id="perfil-plan-formativo-habilidad" type="select" className="form-control" name="habilidadId">
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
              <AvGroup>
                <Label for="perfil-plan-formativo-interes">
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.interes">Interes</Translate>
                </Label>
                <AvInput id="perfil-plan-formativo-interes" type="select" className="form-control" name="interesId">
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
                <Label for="perfil-plan-formativo-planFormativo">
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.planFormativo">Plan Formativo</Translate>
                </Label>
                <AvInput id="perfil-plan-formativo-planFormativo" type="select" className="form-control" name="planFormativoId">
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
              <Button tag={Link} id="cancel-save" to="/perfil-plan-formativo" replace color="info">
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
  habilidads: storeState.habilidad.entities,
  interes: storeState.interes.entities,
  planFormativos: storeState.planFormativo.entities,
  perfilPlanFormativoEntity: storeState.perfilPlanFormativo.entity,
  loading: storeState.perfilPlanFormativo.loading,
  updating: storeState.perfilPlanFormativo.updating,
  updateSuccess: storeState.perfilPlanFormativo.updateSuccess
});

const mapDispatchToProps = {
  getHabilidads,
  getInteres,
  getPlanFormativos,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PerfilPlanFormativoUpdate);
