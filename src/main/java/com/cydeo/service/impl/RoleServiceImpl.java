package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractMapService<RoleDTO,Long> implements RoleService{

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        super.save(roleDTO.getId(), roleDTO);
        return roleDTO;
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void update(RoleDTO object) {
        super.update(object.getId(),object);

    }
}
