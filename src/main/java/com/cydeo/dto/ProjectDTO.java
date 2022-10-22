package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    private String projectName;
    private String projectCode;
    private UserDTO manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String projectDetails;
    private Status projectStatus;
    private int completedTaskCounts;
    private int uncompletedTaskCounts;

    public ProjectDTO(String projectName, String projectCode, UserDTO manager, LocalDate startDate, LocalDate endDate, String projectDetails, Status projectStatus) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.manager = manager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetails = projectDetails;
        this.projectStatus = projectStatus;
    }
}
