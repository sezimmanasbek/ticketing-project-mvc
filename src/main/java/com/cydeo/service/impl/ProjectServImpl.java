package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {


    TaskService taskService;

    public ProjectServImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

//    @Override
//    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
//
//        List<ProjectDTO> projectList = findAll().stream().
//                filter(project -> project.getManager().equals(manager)).map(project -> {
//                    List<TaskDTO> taskList = taskService.findTasksByManager(manager);
//                    int completedTaskCount= (int) taskList.stream().filter(t -> t.getProject().equals(project) && t.getTaskStatus() ==Status.COMPLETE).count();
//                    int uncompletedTaskCount= (int) taskList.stream().filter(t -> t.getProject().equals(project) && t.getTaskStatus() !=Status.COMPLETE).count();
//
//              project.setCompletedTaskCounts(completedTaskCount);
//                project.setUncompletedTaskCounts(uncompletedTaskCount);
//
//                return project;
//                }).collect(Collectors.toList());
//
//        return projectList;
//
//    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project -> project.getManager().equals(manager))
                        .map( project -> {

                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int)taskList.stream().filter(t ->  t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();

                            int unfinishedTaskCounts = (int)taskList.stream().filter(t ->  t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompletedTaskCounts(completeTaskCounts);
                            project.setUncompletedTaskCounts(unfinishedTaskCounts);

                            return project;

                        }).collect(Collectors.toList());


        return projectList;
    }
}
