import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './habilidad-usuario.reducer';
import { IHabilidadUsuario } from 'app/shared/model/habilidad-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHabilidadUsuarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const HabilidadUsuario = (props: IHabilidadUsuarioProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { habilidadUsuarioList, match } = props;
  return (
    <div>
      <h2 id="habilidad-usuario-heading">
        <Translate contentKey="experisFormacionApp.habilidadUsuario.home.title">Habilidad Usuarios</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.habilidadUsuario.home.createLabel">Create new Habilidad Usuario</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {habilidadUsuarioList && habilidadUsuarioList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.habilidadUsuario.habilidad">Habilidad</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.habilidadUsuario.usuario">Usuario</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {habilidadUsuarioList.map((habilidadUsuario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${habilidadUsuario.id}`} color="link" size="sm">
                      {habilidadUsuario.id}
                    </Button>
                  </td>
                  <td>
                    {habilidadUsuario.habilidadId ? (
                      <Link to={`habilidad/${habilidadUsuario.habilidadId}`}>{habilidadUsuario.habilidadId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {habilidadUsuario.usuarioId ? (
                      <Link to={`usuario/${habilidadUsuario.usuarioId}`}>{habilidadUsuario.usuarioId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${habilidadUsuario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${habilidadUsuario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${habilidadUsuario.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="experisFormacionApp.habilidadUsuario.home.notFound">No Habilidad Usuarios found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ habilidadUsuario }: IRootState) => ({
  habilidadUsuarioList: habilidadUsuario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HabilidadUsuario);
