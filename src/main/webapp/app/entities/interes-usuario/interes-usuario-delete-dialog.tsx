import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IInteresUsuario } from 'app/shared/model/interes-usuario.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './interes-usuario.reducer';

export interface IInteresUsuarioDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteresUsuarioDeleteDialog = (props: IInteresUsuarioDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/interes-usuario');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.interesUsuarioEntity.id);
  };

  const { interesUsuarioEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="experisFormacionApp.interesUsuario.delete.question">
        <Translate contentKey="experisFormacionApp.interesUsuario.delete.question" interpolate={{ id: interesUsuarioEntity.id }}>
          Are you sure you want to delete this InteresUsuario?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-interesUsuario" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ interesUsuario }: IRootState) => ({
  interesUsuarioEntity: interesUsuario.entity,
  updateSuccess: interesUsuario.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteresUsuarioDeleteDialog);
