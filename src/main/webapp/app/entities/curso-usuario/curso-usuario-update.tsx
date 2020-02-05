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
import { IUsuario } from 'app/shared/model/usuario.model';
import { getEntities as getUsuarios } from 'app/entities/usuario/usuario.reducer';
import { getEntity, updateEntity, createEntity, reset } from './curso-usuario.reducer';
import { ICursoUsuario } from 'app/shared/model/curso-usuario.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICursoUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursoUsuarioUpdate = (props: ICursoUsuarioUpdateProps) => {
  const [cursoId, setCursoId] = useState('0');
  const [usuarioId, setUsuarioId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cursoUsuarioEntity, cursos, usuarios, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/curso-usuario');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCursos();
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
        ...cursoUsuarioEntity,
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
          <h2 id="experisFormacionApp.cursoUsuario.home.createOrEditLabel">
            <Translate contentKey="experisFormacionApp.cursoUsuario.home.createOrEditLabel">Create or edit a CursoUsuario</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cursoUsuarioEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="curso-usuario-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="curso-usuario-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="curso-usuario-curso">
                  <Translate contentKey="experisFormacionApp.cursoUsuario.curso">Curso</Translate>
                </Label>
                <AvInput id="curso-usuario-curso" type="select" className="form-control" name="cursoId">
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
                <Label for="curso-usuario-usuario">
                  <Translate contentKey="experisFormacionApp.cursoUsuario.usuario">Usuario</Translate>
                </Label>
                <AvInput id="curso-usuario-usuario" type="select" className="form-control" name="usuarioId">
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
              <Button tag={Link} id="cancel-save" to="/curso-usuario" replace color="info">
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
  usuarios: storeState.usuario.entities,
  cursoUsuarioEntity: storeState.cursoUsuario.entity,
  loading: storeState.cursoUsuario.loading,
  updating: storeState.cursoUsuario.updating,
  updateSuccess: storeState.cursoUsuario.updateSuccess
});

const mapDispatchToProps = {
  getCursos,
  getUsuarios,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoUsuarioUpdate);
