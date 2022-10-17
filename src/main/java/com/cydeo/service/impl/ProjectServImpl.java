package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {


    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {

        if(projectDTO.getProjectStatus() == null){
            projectDTO.setProjectStatus(Status.OPEN);
        }
        return super.save(projectDTO.getProjectCode(),projectDTO);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public ProjectDTO findById(String s) {
        return super.findById(s);
    }

    @Override
    public void deleteById(String s) {
        super.deleteById(s);

    }

    @Override
    public void update(ProjectDTO object) {
        if(object.getProjectStatus() == null){
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.COMPLETE);
        super.save(projectDTO.getProjectCode(),projectDTO);
    }
}
