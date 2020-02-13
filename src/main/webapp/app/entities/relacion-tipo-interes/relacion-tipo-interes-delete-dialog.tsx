import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRelacionTipoInteres } from 'app/shared/model/relacion-tipo-interes.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './relacion-tipo-interes.reducer';

export interface IRelacionTipoInteresDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelacionTipoInteresDeleteDialog = (props: IRelacionTipoInteresDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/relacion-tipo-interes' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.relacionTipoInteresEntity.id);
  };

  const { relacionTipoInteresEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="experisFormacionApp.relacionTipoInteres.delete.question">
        <Translate contentKey="experisFormacionApp.relacionTipoInteres.delete.question" interpolate={{ id: relacionTipoInteresEntity.id }}>
          Are you sure you want to delete this RelacionTipoInteres?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-relacionTipoInteres" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ relacionTipoInteres }: IRootState) => ({
  relacionTipoInteresEntity: relacionTipoInteres.entity,
  updateSuccess: relacionTipoInteres.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelacionTipoInteresDeleteDialog);
