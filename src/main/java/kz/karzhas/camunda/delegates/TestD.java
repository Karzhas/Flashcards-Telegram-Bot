package kz.karzhas.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TestD implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println(delegateExecution.getVariable("front"));
        String s  = (String)delegateExecution.getVariable("front");
    }
}
