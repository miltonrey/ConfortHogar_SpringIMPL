package org.camunda.platform.runtime.example;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("eligibilityCheck")
public class EligibilityHandler implements ExternalTaskHandler {

  @Override
  public void execute(ExternalTask extTask, ExternalTaskService extTaskService) {

    String name = extTask.getVariable("name");
    long ancho = Long.parseLong(extTask.getVariable("ancho").toString());
    long alto = Long.parseLong(extTask.getVariable("alto").toString());
    long profundo = Long.parseLong(extTask.getVariable("profundo").toString());
    String email = extTask.getVariable("email");
    String madera = extTask.getVariable("madera");
    long status = 0;
    long precio = 0;
    String rejectReason = "";

    System.out.println("name: " + name);
    System.out.println("Ancho: " + ancho);
    System.out.println("Alto: " + alto);
    System.out.println("Profundo: " + profundo);
    System.out.println("email: " + email);
    System.out.println("Tipo madera: " + madera);
    System.out.println("status: " + status);
    System.out.println("rejectReason: " + rejectReason);
    status = ancho * alto * profundo;

    if (status < 1 && status > 0) {
      if (madera.equals("blandas")) {
        precio = 100000;
        rejectReason = "el precio del mueble es: " + precio;
      }
      if (madera.equals("duras")) {
        precio = 120000;
        rejectReason = "el precio del mueble es: " + precio;
      } else {
        precio = 150000;
        rejectReason = "el precio del mueble es: " + precio;
      }

    } else if (status > 1 && status < 5) {
      if (madera.equals("blandas")) {
        precio = 150000;
        rejectReason = "el precio del mueble es: " + precio;
      }
      if (madera.equals("duras")) {
        precio = 170000;
        rejectReason = "el precio del mueble es: " + precio;
      } else {
        precio = 200000;
        rejectReason = "el precio del mueble es: " + precio;
      }
    } else {
      if (madera.equals("blandas")) {
        precio = 200000;
        rejectReason = "el precio del mueble es: " + precio;
      }
      if (madera.equals("duras")) {
        precio = 220000;
        rejectReason = "el precio del mueble es: " + precio;
      } else {
        precio = 250000;
        rejectReason = "el precio del mueble es: " + precio;
      }
    }

    VariableMap variables = Variables.createVariables();
    variables.put("status", status);
    variables.put("rejectReason", rejectReason);

    extTaskService.complete(extTask, variables);

  }

}
