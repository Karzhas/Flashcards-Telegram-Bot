package kz.karzhas.camunda.model;


import java.util.ArrayList;
import java.util.List;

public class Variables {

    List<Variable> variables = new ArrayList<>();

    public void addVariable(Variable variable){
        variables.add(variable);
    }

    @Override
    public String toString() {
        StringBuilder json = new StringBuilder("{\"variables\":\n{");
        for(int i = 0; i < variables.size(); i++){
            Variable v = variables.get(i);
            json.append(v);
            if(i != variables.size()-1)
                json.append(",\n");
            else
                json.append("}");
        }
        json.append("}");
        return json.toString();
    }

    public static class Variable{
        String name;
        String type;
        Long intValue;
        Boolean booleanValue;
        String stringValue;

        public Variable(String name, String type, Long intValue) {
            this.name = name;
            this.type = type;
            this.intValue = intValue;
        }

        public Variable(String name, String type, Boolean booleanValue) {
            this.name = name;
            this.type = type;
            this.booleanValue = booleanValue;
        }

        public Variable(String name, String type, String stringValue) {
            this.name = name;
            this.type = type;
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            String json =
                    "\"" + name + "\": ";
            if (type.equalsIgnoreCase("string"))
                json += "{\"value\": " + "\"" + stringValue + "\"}";
            if(type.equalsIgnoreCase("boolean")){
                if (booleanValue)
                    json += "{\"value\": " + "true}";
                else
                    json += "{\"value\": " + "false}";
            }

            return json;

        }
    }

}
