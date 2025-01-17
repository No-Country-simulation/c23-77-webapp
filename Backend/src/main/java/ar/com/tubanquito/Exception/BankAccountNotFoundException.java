package ar.com.tubanquito.Exception;

public class BankAccountNotFoundException extends RuntimeException {
    
    BankAccountNotFoundException(String e){
        super(e);
    }
}
