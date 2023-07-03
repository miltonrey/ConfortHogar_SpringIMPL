package com.tekkytalks.camunda;

import org.camunda.bpm.engine.OptimisticLockingException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.camunda.bpm.engine.variable.VariableMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@JsonIgnoreProperties(value = { "execution" })
public class UserTaskController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasks() {
        // Obtener todas las tareas de usuario pendientes
        List<Task> tasks = taskService.createTaskQuery().list();

        // Convertir las tareas a objetos TaskDto
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setAssignee(task.getAssignee());
            // Asignar otros campos según tus necesidades
            taskDtos.add(taskDto);
        }

        return ResponseEntity.ok(taskDtos);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("taskId") String taskId) {

        // Obtener la tarea por ID
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        // Obtener las variables de la tarea
        Map<String, Object> variables = taskService.getVariables(taskId);

        // Crear un objeto TaskDto y asignar los valores de la tarea y las variables
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setAssignee(task.getAssignee());
        taskDto.setFormName((String) variables.get("name"));
        taskDto.setFormAncho((Long) variables.get("ancho"));
        taskDto.setFormAlto((Long) variables.get("alto"));
        taskDto.setFormProfundo((Long) variables.get("profundo"));
        taskDto.setFormEmail((String) variables.get("email"));
        taskDto.setFormMadera((String) variables.get("madera"));
        taskDto.setRejectReason((String) variables.get("rejectReason"));

        return ResponseEntity.ok(taskDto);
    }


    @PostMapping("/tasks/{taskId}/validate")
    public ResponseEntity<String> validateTask(@PathVariable("taskId") String taskId,
                                               @RequestBody Map<String, Object> formData) {
        String Validate="";
        VariableMap variables = Variables.createVariables();
        variables.put("true", Validate);
        taskService.complete(taskId, variables);
        return ResponseEntity.ok("Tarea validada exitosamente");
    }


    public static class TaskDto {
        private String id;
        private String name;
        private String assignee;
        private String formName;
        private Long formAncho;
        private Long formAlto;
        private Long formProfundo;
        private String formEmail;
        private String formMadera;

        private String rejectReason;

        // Agregar otros campos según tus necesidades

        public String getRejectReason() {
            return rejectReason;
        }

        public void setRejectReason(String rejectReason) {
            this.rejectReason = rejectReason;
        }

        // Agregar getters y setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getFormName() {
            return formName;
        }

        public void setFormName(String formName) {
            this.formName = formName;
        }

        public Long getFormAncho() {
            return formAncho;
        }

        public void setFormAncho(Long formAncho) {
            this.formAncho = formAncho;
        }

        public Long getFormAlto() {
            return formAlto;
        }

        public void setFormAlto(Long formAlto) {
            this.formAlto = formAlto;
        }

        public Long getFormProfundo() {
            return formProfundo;
        }

        public void setFormProfundo(Long formProfundo) {
            this.formProfundo = formProfundo;
        }

        public String getFormEmail() {
            return formEmail;
        }

        public void setFormEmail(String formEmail) {
            this.formEmail = formEmail;
        }

        public String getFormMadera() {
            return formMadera;
        }

        public void setFormMadera(String formMadera) {
            this.formMadera = formMadera;
        }
    }

}
