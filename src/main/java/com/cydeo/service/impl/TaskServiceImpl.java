package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        if(taskDTO.getTaskStatus() == null){
            taskDTO.setTaskStatus(Status.OPEN);
        }
        if(taskDTO.getAssignedDate() == null){
            taskDTO.setAssignedDate(LocalDate.now());
        }

        if(taskDTO.getId() == null){
            taskDTO.setId(UUID.randomUUID().getMostSignificantBits());
        }
        return super.save(taskDTO.getId(),taskDTO);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void update(TaskDTO object) {
        if(object.getAssignedDate() == null){
            object.setAssignedDate(super.findById(object.getId()).getAssignedDate());
        }

        if(object.getTaskStatus() == null){
            object.setTaskStatus(super.findById(object.getId()).getTaskStatus());
        }

        super.update(object.getId(),object);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO user) {
        return findAll().stream().filter(task->task.getProject().getManager().equals(user)).collect(Collectors.toList());
    }
}
