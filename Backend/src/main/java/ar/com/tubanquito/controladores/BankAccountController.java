package ar.com.tubanquito.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.tubanquito.dto.request.AccountRequestEditDTO;
import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.servicios.CuentaBancariaServicioUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    
    @Autowired
    private CuentaBancariaServicioUI cuentaBancariaServicio;
   

    @PostMapping("")
    public ResponseEntity<?> createBankAccount(@RequestBody CreateBankAccountDTO account) throws Exception {
        Object response = cuentaBancariaServicio.createAccount(account);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(cuentaBancariaServicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaBancariaServicio.getAccountById(id));
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getAccountsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaBancariaServicio.getAccountsByIdUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Long idAccount, @PathVariable Long idUser) throws Exception{
        cuentaBancariaServicio.deleteAccount(idUser, idAccount);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{idAccount}/users/{idUser}")
    public ResponseEntity<?> editAccount(@PathVariable Long idAccount, @PathVariable Long idUser, @RequestBody AccountRequestEditDTO accountEdit) {
        cuentaBancariaServicio.editAccount(idUser, idAccount, accountEdit);
        return ResponseEntity.ok().build();
    }
}
