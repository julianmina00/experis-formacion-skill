import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPlanFormativo } from 'app/shared/model/plan-formativo.model';
import { getEntities as getPlanFormativos } from 'app/entities/plan-formativo/plan-formativo.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { getEntity, updateEntity, createEntity, reset } from './plan-formativo-usuario.reducer';
import { IPlanFormativoUsuario } from 'app/shared/model/plan-formativo-usuario.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPlanFormativoUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlanFormativoUsuarioUpdate = (props: IPlanFormativoUsuarioUpdateProps) => {
  const [planFormativoId, setPlanFormativoId] = useState('0');
  const [usuarioId, setUsuarioId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { planFormativoUsuarioEntity, planFormativos, usuarios, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/plan-formativo-usuario');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getPlanFormativos();
    props.getUsuarios();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...planFormativoUsuarioEntity,
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
          <h2 id="experisFormacionApp.planFormativoUsuario.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.planFormativoUsuario.home.createOrEditLabel">
              Create or edit a PlanFormativoUsuario
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : planFormativoUsuarioEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="plan-formativo-usuario-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="plan-formativo-usuario-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="plan-formativo-usuario-planFormativo">
                  <Translate contentKey="experisFormacionApp.planFormativoUsuario.planFormativo">Plan Formativo</Translate>
                </Label>
                <AvInput id="plan-formativo-usuario-planFormativo" type="select" className="form-control" name="planFormativoId">
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
              <AvGroup>
                <Label for="plan-formativo-usuario-usuario">
                  <Translate contentKey="experisFormacionApp.planFormativoUsuario.usuario">Usuario</Translate>
                </Label>
                <AvInput id="plan-formativo-usuario-usuario" type="select" className="form-control" name="usuarioId">
                  <option value="" key="0" />
                  {usuarios
                    ? usuarios.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/plan-formativo-usuario" replace color="info">
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
  planFormativos: storeState.planFormativo.entities,
  usuarios: storeState.usuario.entities,
  planFormativoUsuarioEntity: storeState.planFormativoUsuario.entity,
  loading: storeState.planFormativoUsuario.loading,
  updating: storeState.planFormativoUsuario.updating,
  updateSuccess: storeState.planFormativoUsuario.updateSuccess
});

const mapDispatchToProps = {
  getPlanFormativos,
  getUsuarios,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlanFormativoUsuarioUpdate);
