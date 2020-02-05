import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './plan-formativo-usuario.reducer';
import { IPlanFormativoUsuario } from 'app/shared/model/plan-formativo-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlanFormativoUsuarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const PlanFormativoUsuario = (props: IPlanFormativoUsuarioProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { planFormativoUsuarioList, match } = props;
  return (
    <div>
      <h2 id="plan-formativo-usuario-heading">
        <Translate contentKey="experisFormacionApp.planFormativoUsuario.home.title">Plan Formativo Usuarios</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.planFormativoUsuario.home.createLabel">Create new Plan Formativo Usuario</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {planFormativoUsuarioList && planFormativoUsuarioList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.planFormativoUsuario.planFormativo">Plan Formativo</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.planFormativoUsuario.usuario">Usuario</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {planFormativoUsuarioList.map((planFormativoUsuario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${planFormativoUsuario.id}`} color="link" size="sm">
                      {planFormativoUsuario.id}
                    </Button>
                  </td>
                  <td>
                    {planFormativoUsuario.planFormativoId ? (
                      <Link to={`plan-formativo/${planFormativoUsuario.planFormativoId}`}>{planFormativoUsuario.planFormativoId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {planFormativoUsuario.usuarioId ? (
                      <Link to={`usuario/${planFormativoUsuario.usuarioId}`}>{planFormativoUsuario.usuarioId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${planFormativoUsuario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${planFormativoUsuario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${planFormativoUsuario.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">
            <Translate contentKey="experisFormacionApp.planFormativoUsuario.home.notFound">No Plan Formativo Usuarios found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ planFormativoUsuario }: IRootState) => ({
  planFormativoUsuarioList: planFormativoUsuario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlanFormativoUsuario);
