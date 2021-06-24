package kz.karzhas.camunda.delegates;

public class ProcessInstance {

    private String id;

    public ProcessInstance(){}

    public ProcessInstance(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
