package ar.com.tubanquito.infra.error;

public class BankAccountNotFoundException extends RuntimeException {
 
    public BankAccountNotFoundException(String e){
        super(e);
    }
}
