import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRelacionTipoHabilidad } from 'app/shared/model/relacion-tipo-habilidad.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './relacion-tipo-habilidad.reducer';

export interface IRelacionTipoHabilidadDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelacionTipoHabilidadDeleteDialog = (props: IRelacionTipoHabilidadDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/relacion-tipo-habilidad' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.relacionTipoHabilidadEntity.id);
  };

  const { relacionTipoHabilidadEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="experisFormacionApp.relacionTipoHabilidad.delete.question">
        <Translate
          contentKey="experisFormacionApp.relacionTipoHabilidad.delete.question"
          interpolate={{ id: relacionTipoHabilidadEntity.id }}
        >
          Are you sure you want to delete this RelacionTipoHabilidad?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-relacionTipoHabilidad" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ relacionTipoHabilidad }: IRootState) => ({
  relacionTipoHabilidadEntity: relacionTipoHabilidad.entity,
  updateSuccess: relacionTipoHabilidad.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelacionTipoHabilidadDeleteDialog);
