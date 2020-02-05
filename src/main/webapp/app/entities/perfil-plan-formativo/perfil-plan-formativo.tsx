import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './perfil-plan-formativo.reducer';
import { IPerfilPlanFormativo } from 'app/shared/model/perfil-plan-formativo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPerfilPlanFormativoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const PerfilPlanFormativo = (props: IPerfilPlanFormativoProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { perfilPlanFormativoList, match } = props;
  return (
    <div>
      <h2 id="perfil-plan-formativo-heading">
        <Translate contentKey="experisFormacionApp.perfilPlanFormativo.home.title">Perfil Plan Formativos</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.perfilPlanFormativo.home.createLabel">Create new Perfil Plan Formativo</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {perfilPlanFormativoList && perfilPlanFormativoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.habilidad">Habilidad</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.interes">Interes</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.perfilPlanFormativo.planFormativo">Plan Formativo</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {perfilPlanFormativoList.map((perfilPlanFormativo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${perfilPlanFormativo.id}`} color="link" size="sm">
                      {perfilPlanFormativo.id}
                    </Button>
                  </td>
                  <td>
                    {perfilPlanFormativo.habilidadId ? (
                      <Link to={`habilidad/${perfilPlanFormativo.habilidadId}`}>{perfilPlanFormativo.habilidadId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {perfilPlanFormativo.interesId ? (
                      <Link to={`interes/${perfilPlanFormativo.interesId}`}>{perfilPlanFormativo.interesId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {perfilPlanFormativo.planFormativoId ? (
                      <Link to={`plan-formativo/${perfilPlanFormativo.planFormativoId}`}>{perfilPlanFormativo.planFormativoId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${perfilPlanFormativo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${perfilPlanFormativo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${perfilPlanFormativo.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="experisFormacionApp.perfilPlanFormativo.home.notFound">No Perfil Plan Formativos found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ perfilPlanFormativo }: IRootState) => ({
  perfilPlanFormativoList: perfilPlanFormativo.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PerfilPlanFormativo);
