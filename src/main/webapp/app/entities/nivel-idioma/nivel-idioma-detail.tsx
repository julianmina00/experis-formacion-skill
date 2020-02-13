import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nivel-idioma.reducer';
import { INivelIdioma } from 'app/shared/model/nivel-idioma.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INivelIdiomaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NivelIdiomaDetail = (props: INivelIdiomaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { nivelIdiomaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.nivelIdioma.detail.title">NivelIdioma</Translate> [<b>{nivelIdiomaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nivel">
              <Translate contentKey="experisFormacionApp.nivelIdioma.nivel">Nivel</Translate>
            </span>
          </dt>
          <dd>{nivelIdiomaEntity.nivel}</dd>
          <dt>
            <span id="descripcion">
              <Translate contentKey="experisFormacionApp.nivelIdioma.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{nivelIdiomaEntity.descripcion}</dd>
        </dl>
        <Button tag={Link} to="/nivel-idioma" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nivel-idioma/${nivelIdiomaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ nivelIdioma }: IRootState) => ({
  nivelIdiomaEntity: nivelIdioma.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NivelIdiomaDetail);
