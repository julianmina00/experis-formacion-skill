import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './relacion-tipo-habilidad.reducer';
import { IRelacionTipoHabilidad } from 'app/shared/model/relacion-tipo-habilidad.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRelacionTipoHabilidadDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelacionTipoHabilidadDetail = (props: IRelacionTipoHabilidadDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { relacionTipoHabilidadEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.detail.title">RelacionTipoHabilidad</Translate> [
          <b>{relacionTipoHabilidadEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="profundidad">
              <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.profundidad">Profundidad</Translate>
            </span>
          </dt>
          <dd>{relacionTipoHabilidadEntity.profundidad}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.padre">Padre</Translate>
          </dt>
          <dd>{relacionTipoHabilidadEntity.padreId ? relacionTipoHabilidadEntity.padreId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.relacionTipoHabilidad.hija">Hija</Translate>
          </dt>
          <dd>{relacionTipoHabilidadEntity.hijaId ? relacionTipoHabilidadEntity.hijaId : ''}</dd>
        </dl>
        <Button tag={Link} to="/relacion-tipo-habilidad" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/relacion-tipo-habilidad/${relacionTipoHabilidadEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ relacionTipoHabilidad }: IRootState) => ({
  relacionTipoHabilidadEntity: relacionTipoHabilidad.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelacionTipoHabilidadDetail);
