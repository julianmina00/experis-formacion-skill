import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/curso">
      <Translate contentKey="global.menu.entities.curso" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/plan-formativo">
      <Translate contentKey="global.menu.entities.planFormativo" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tipo-habilidad">
      <Translate contentKey="global.menu.entities.tipoHabilidad" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tipo-interes">
      <Translate contentKey="global.menu.entities.tipoInteres" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/usuario">
      <Translate contentKey="global.menu.entities.usuario" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/habilidad">
      <Translate contentKey="global.menu.entities.habilidad" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/interes">
      <Translate contentKey="global.menu.entities.interes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/curso-plan-formativo">
      <Translate contentKey="global.menu.entities.cursoPlanFormativo" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/perfil-plan-formativo">
      <Translate contentKey="global.menu.entities.perfilPlanFormativo" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/curso-usuario">
      <Translate contentKey="global.menu.entities.cursoUsuario" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/habilidad-usuario">
      <Translate contentKey="global.menu.entities.habilidadUsuario" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/interes-usuario">
      <Translate contentKey="global.menu.entities.interesUsuario" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/plan-formativo-usuario">
      <Translate contentKey="global.menu.entities.planFormativoUsuario" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/contenido-curso">
      <Translate contentKey="global.menu.entities.contenidoCurso" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/relacion-tipo-habilidad">
      <Translate contentKey="global.menu.entities.relacionTipoHabilidad" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/relacion-tipo-interes">
      <Translate contentKey="global.menu.entities.relacionTipoInteres" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/nivel-idioma">
      <Translate contentKey="global.menu.entities.nivelIdioma" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/idioma-usuario">
      <Translate contentKey="global.menu.entities.idiomaUsuario" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
