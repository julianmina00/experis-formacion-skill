import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICursoPlanFormativo } from 'app/shared/model/curso-plan-formativo.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './curso-plan-formativo.reducer';

export interface ICursoPlanFormativoDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CursoPlanFormativoDeleteDialog = (props: ICursoPlanFormativoDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/curso-plan-formativo');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.cursoPlanFormativoEntity.id);
  };

  const { cursoPlanFormativoEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="experisFormacionApp.cursoPlanFormativo.delete.question">
        <Translate contentKey="experisFormacionApp.cursoPlanFormativo.delete.question" interpolate={{ id: cursoPlanFormativoEntity.id }}>
          Are you sure you want to delete this CursoPlanFormativo?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-cursoPlanFormativo" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ cursoPlanFormativo }: IRootState) => ({
  cursoPlanFormativoEntity: cursoPlanFormativo.entity,
  updateSuccess: cursoPlanFormativo.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CursoPlanFormativoDeleteDialog);
