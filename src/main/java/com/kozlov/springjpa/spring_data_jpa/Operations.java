package com.kozlov.springjpa.spring_data_jpa;

public enum Operations {
   MORETHAN ("moreThan"),
   LESSTHAN ("lessThan"),
   EQUAL ("equal");

   private String operation;

   Operations(String operation) {
       this.operation = operation;
   }

   public String getOperation() {
       return operation;
   }

   public static boolean contains(String s) {
       for(Operations o : Operations.values()) {
           if(s.equals(o.getOperation())) {
               return true;
           }
       }
       return false;
   }
}
