package ar.com.tubanquito.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.servicios.CuentaBancariaServicio;
import ar.com.tubanquito.servicios.CuentaBancariaServicioUI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaControlador {
    
    @Autowired
    private CuentaBancariaServicioUI cuentaBancariaServicio;
   
    @GetMapping("")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(cuentaBancariaServicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaBancariaServicio.getAccountById(id));
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getAccountByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaBancariaServicio.getAccountsByIdUser(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createBankAccount(CreateBankAccountDTO account) {
        AccountResponseDTO response = cuentaBancariaServicio.createAccount(account);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Long idAccount, @PathVariable Long idUser){
        cuentaBancariaServicio.deleteAccount(idAccount, idAccount);
        return ResponseEntity.ok().build();
    }

    
}
