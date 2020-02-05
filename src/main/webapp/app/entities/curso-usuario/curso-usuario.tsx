import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './curso-usuario.reducer';
import { ICursoUsuario } from 'app/shared/model/curso-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICursoUsuarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CursoUsuario = (props: ICursoUsuarioProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { cursoUsuarioList, match } = props;
  return (
    <div>
      <h2 id="curso-usuario-heading">
        <Translate contentKey="experisFormacionApp.cursoUsuario.home.title">Curso Usuarios</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.cursoUsuario.home.createLabel">Create new Curso Usuario</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cursoUsuarioList && cursoUsuarioList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.cursoUsuario.curso">Curso</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.cursoUsuario.usuario">Usuario</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cursoUsuarioList.map((cursoUsuario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cursoUsuario.id}`} color="link" size="sm">
                      {cursoUsuario.id}
                    </Button>
                  </td>
                  <td>{cursoUsuario.cursoId ? <Link to={`curso/${cursoUsuario.cursoId}`}>{cursoUsuario.cursoId}</Link> : ''}</td>
                  <td>{cursoUsuario.usuarioId ? <Link to={`usuario/${cursoUsuario.usuarioId}`}>{cursoUsuario.usuarioId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cursoUsuario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cursoUsuario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cursoUsuario.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="experisFormacionApp.cursoUsuario.home.notFound">No Curso Usuarios found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ cursoUsuario }: IRootState) => ({
  cursoUsuarioList: cursoUsuario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoUsuario);
