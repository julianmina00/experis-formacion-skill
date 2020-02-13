import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './usuario.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IUsuarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Usuario = (props: IUsuarioProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  useEffect(() => {
    getAllEntities();
  }, []);

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage
    });

  const { usuarioList, match, totalItems } = props;
  return (
    <div>
      <h2 id="usuario-heading">
        <Translate contentKey="experisFormacionApp.usuario.home.title">Usuarios</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.usuario.home.createLabel">Create new Usuario</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {usuarioList && usuarioList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('documento')}>
                  <Translate contentKey="experisFormacionApp.usuario.documento">Documento</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tipoDocumento')}>
                  <Translate contentKey="experisFormacionApp.usuario.tipoDocumento">Tipo Documento</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nombre')}>
                  <Translate contentKey="experisFormacionApp.usuario.nombre">Nombre</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('apellidos')}>
                  <Translate contentKey="experisFormacionApp.usuario.apellidos">Apellidos</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('email')}>
                  <Translate contentKey="experisFormacionApp.usuario.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('telefono')}>
                  <Translate contentKey="experisFormacionApp.usuario.telefono">Telefono</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rol')}>
                  <Translate contentKey="experisFormacionApp.usuario.rol">Rol</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('proyecto')}>
                  <Translate contentKey="experisFormacionApp.usuario.proyecto">Proyecto</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('compania')}>
                  <Translate contentKey="experisFormacionApp.usuario.compania">Compania</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ubicacion')}>
                  <Translate contentKey="experisFormacionApp.usuario.ubicacion">Ubicacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('managerNombre')}>
                  <Translate contentKey="experisFormacionApp.usuario.managerNombre">Manager Nombre</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('managerEmail')}>
                  <Translate contentKey="experisFormacionApp.usuario.managerEmail">Manager Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('talentMentorNombre')}>
                  <Translate contentKey="experisFormacionApp.usuario.talentMentorNombre">Talent Mentor Nombre</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('talentMentorEmail')}>
                  <Translate contentKey="experisFormacionApp.usuario.talentMentorEmail">Talent Mentor Email</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {usuarioList.map((usuario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${usuario.id}`} color="link" size="sm">
                      {usuario.id}
                    </Button>
                  </td>
                  <td>{usuario.documento}</td>
                  <td>{usuario.tipoDocumento}</td>
                  <td>{usuario.nombre}</td>
                  <td>{usuario.apellidos}</td>
                  <td>{usuario.email}</td>
                  <td>{usuario.telefono}</td>
                  <td>{usuario.rol}</td>
                  <td>{usuario.proyecto}</td>
                  <td>{usuario.compania}</td>
                  <td>{usuario.ubicacion}</td>
                  <td>{usuario.managerNombre}</td>
                  <td>{usuario.managerEmail}</td>
                  <td>{usuario.talentMentorNombre}</td>
                  <td>{usuario.talentMentorEmail}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${usuario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${usuario.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${usuario.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
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
            <Translate contentKey="experisFormacionApp.usuario.home.notFound">No Usuarios found</Translate>
          </div>
        )}
      </div>
      <div className={usuarioList && usuarioList.length > 0 ? '' : 'd-none'}>
        <Row className="justify-content-center">
          <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
        </Row>
        <Row className="justify-content-center">
          <JhiPagination
            activePage={paginationState.activePage}
            onSelect={handlePagination}
            maxButtons={5}
            itemsPerPage={paginationState.itemsPerPage}
            totalItems={props.totalItems}
          />
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = ({ usuario }: IRootState) => ({
  usuarioList: usuario.entities,
  totalItems: usuario.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Usuario);
