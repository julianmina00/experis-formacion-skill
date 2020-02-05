import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './curso-plan-formativo.reducer';
import { ICursoPlanFormativo } from 'app/shared/model/curso-plan-formativo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICursoPlanFormativoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CursoPlanFormativo = (props: ICursoPlanFormativoProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { cursoPlanFormativoList, match } = props;
  return (
    <div>
      <h2 id="curso-plan-formativo-heading">
        <Translate contentKey="experisFormacionApp.cursoPlanFormativo.home.title">Curso Plan Formativos</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.cursoPlanFormativo.home.createLabel">Create new Curso Plan Formativo</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cursoPlanFormativoList && cursoPlanFormativoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.cursoPlanFormativo.curso">Curso</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.cursoPlanFormativo.planFormativo">Plan Formativo</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cursoPlanFormativoList.map((cursoPlanFormativo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cursoPlanFormativo.id}`} color="link" size="sm">
                      {cursoPlanFormativo.id}
                    </Button>
                  </td>
                  <td>
                    {cursoPlanFormativo.cursoId ? <Link to={`curso/${cursoPlanFormativo.cursoId}`}>{cursoPlanFormativo.cursoId}</Link> : ''}
                  </td>
                  <td>
                    {cursoPlanFormativo.planFormativoId ? (
                      <Link to={`plan-formativo/${cursoPlanFormativo.planFormativoId}`}>{cursoPlanFormativo.planFormativoId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cursoPlanFormativo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cursoPlanFormativo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cursoPlanFormativo.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="experisFormacionApp.cursoPlanFormativo.home.notFound">No Curso Plan Formativos found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ cursoPlanFormativo }: IRootState) => ({
  cursoPlanFormativoList: cursoPlanFormativo.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoPlanFormativo);
