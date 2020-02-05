import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './interes-usuario.reducer';
import { IInteresUsuario } from 'app/shared/model/interes-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInteresUsuarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const InteresUsuario = (props: IInteresUsuarioProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { interesUsuarioList, match } = props;
  return (
    <div>
      <h2 id="interes-usuario-heading">
        <Translate contentKey="experisFormacionApp.interesUsuario.home.title">Interes Usuarios</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.interesUsuario.home.createLabel">Create new Interes Usuario</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {interesUsuarioList && interesUsuarioList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.interesUsuario.interes">Interes</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.interesUsuario.usuario">Usuario</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {interesUsuarioList.map((interesUsuario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${interesUsuario.id}`} color="link" size="sm">
                      {interesUsuario.id}
                    </Button>
                  </td>
                  <td>
                    {interesUsuario.interesId ? <Link to={`interes/${interesUsuario.interesId}`}>{interesUsuario.interesId}</Link> : ''}
                  </td>
                  <td>
                    {interesUsuario.usuarioId ? <Link to={`usuario/${interesUsuario.usuarioId}`}>{interesUsuario.usuarioId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${interesUsuario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${interesUsuario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${interesUsuario.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="experisFormacionApp.interesUsuario.home.notFound">No Interes Usuarios found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ interesUsuario }: IRootState) => ({
  interesUsuarioList: interesUsuario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteresUsuario);
