import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IHabilidadUsuario } from 'app/shared/model/habilidad-usuario.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './habilidad-usuario.reducer';

export interface IHabilidadUsuarioDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HabilidadUsuarioDeleteDialog = (props: IHabilidadUsuarioDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/habilidad-usuario');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.habilidadUsuarioEntity.id);
  };

  const { habilidadUsuarioEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="experisFormacionApp.habilidadUsuario.delete.question">
        <Translate contentKey="experisFormacionApp.habilidadUsuario.delete.question" interpolate={{ id: habilidadUsuarioEntity.id }}>
          Are you sure you want to delete this HabilidadUsuario?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-habilidadUsuario" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ habilidadUsuario }: IRootState) => ({
  habilidadUsuarioEntity: habilidadUsuario.entity,
  updateSuccess: habilidadUsuario.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HabilidadUsuarioDeleteDialog);
