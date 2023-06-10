package com.tekkytalks.camunda;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class NotifyApplicant implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // TODO Auto-generated method stub

        String email = (String) execution.getVariable("email");
        long status = (long) execution.getVariable("status");
        String rejectReason = (String) execution.getVariable("rejectReason");

        System.out.println("email" + email);
        System.out.println("status" + status);
        System.out.println("rejectReason" + rejectReason);

        SendEmail obj = new SendEmail();

        obj.sendEmail(email, status, rejectReason);

    }

}
