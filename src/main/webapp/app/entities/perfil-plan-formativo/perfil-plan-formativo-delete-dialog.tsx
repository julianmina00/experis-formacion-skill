import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPerfilPlanFormativo } from 'app/shared/model/perfil-plan-formativo.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './perfil-plan-formativo.reducer';

export interface IPerfilPlanFormativoDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PerfilPlanFormativoDeleteDialog = (props: IPerfilPlanFormativoDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/perfil-plan-formativo');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.perfilPlanFormativoEntity.id);
  };

  const { perfilPlanFormativoEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="experisFormacionApp.perfilPlanFormativo.delete.question">
        <Translate contentKey="experisFormacionApp.perfilPlanFormativo.delete.question" interpolate={{ id: perfilPlanFormativoEntity.id }}>
          Are you sure you want to delete this PerfilPlanFormativo?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-perfilPlanFormativo" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ perfilPlanFormativo }: IRootState) => ({
  perfilPlanFormativoEntity: perfilPlanFormativo.entity,
  updateSuccess: perfilPlanFormativo.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PerfilPlanFormativoDeleteDialog);
