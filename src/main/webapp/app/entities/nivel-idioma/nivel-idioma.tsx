import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nivel-idioma.reducer';
import { INivelIdioma } from 'app/shared/model/nivel-idioma.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INivelIdiomaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NivelIdioma = (props: INivelIdiomaProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { nivelIdiomaList, match } = props;
  return (
    <div>
      <h2 id="nivel-idioma-heading">
        <Translate contentKey="experisFormacionApp.nivelIdioma.home.title">Nivel Idiomas</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="experisFormacionApp.nivelIdioma.home.createLabel">Create new Nivel Idioma</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {nivelIdiomaList && nivelIdiomaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.nivelIdioma.nivel">Nivel</Translate>
                </th>
                <th>
                  <Translate contentKey="experisFormacionApp.nivelIdioma.descripcion">Descripcion</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nivelIdiomaList.map((nivelIdioma, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nivelIdioma.id}`} color="link" size="sm">
                      {nivelIdioma.id}
                    </Button>
                  </td>
                  <td>{nivelIdioma.nivel}</td>
                  <td>{nivelIdioma.descripcion}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nivelIdioma.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nivelIdioma.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nivelIdioma.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="experisFormacionApp.nivelIdioma.home.notFound">No Nivel Idiomas found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ nivelIdioma }: IRootState) => ({
  nivelIdiomaList: nivelIdioma.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NivelIdioma);
