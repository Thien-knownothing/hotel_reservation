package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;



        if(emailValidate(email) == true ){
            this.email = email;
        }else{
            throw new IllegalArgumentException("Check the email format");
        }


    }
    public boolean emailValidate (String email){
        String emailRegex = "^(.+)@(.+).(.+)$";
        boolean isValid = false;
        try{
            Pattern pattern = Pattern.compile(emailRegex);
            isValid = pattern.matcher(email).matches();
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return isValid;
    }



    @Override
    public String toString(){
        return "Customer first Name:"+firstName + "\n" +
                "Customer last Name:"+lastName + "\n"+
                "Customer email:"+email;
    }

    public String getEmail() {

        return this.email;
    }
}
