import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './relacion-tipo-interes.reducer';
import { IRelacionTipoInteres } from 'app/shared/model/relacion-tipo-interes.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRelacionTipoInteresDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelacionTipoInteresDetail = (props: IRelacionTipoInteresDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { relacionTipoInteresEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="experisFormacionApp.relacionTipoInteres.detail.title">RelacionTipoInteres</Translate> [
          <b>{relacionTipoInteresEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="profundidad">
              <Translate contentKey="experisFormacionApp.relacionTipoInteres.profundidad">Profundidad</Translate>
            </span>
          </dt>
          <dd>{relacionTipoInteresEntity.profundidad}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.relacionTipoInteres.padre">Padre</Translate>
          </dt>
          <dd>{relacionTipoInteresEntity.padreId ? relacionTipoInteresEntity.padreId : ''}</dd>
          <dt>
            <Translate contentKey="experisFormacionApp.relacionTipoInteres.hija">Hija</Translate>
          </dt>
          <dd>{relacionTipoInteresEntity.hijaId ? relacionTipoInteresEntity.hijaId : ''}</dd>
        </dl>
        <Button tag={Link} to="/relacion-tipo-interes" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/relacion-tipo-interes/${relacionTipoInteresEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ relacionTipoInteres }: IRootState) => ({
  relacionTipoInteresEntity: relacionTipoInteres.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelacionTipoInteresDetail);
