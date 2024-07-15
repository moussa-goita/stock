package odk.kalanso.stock.controllers;

import odk.kalanso.stock.entities.*;
import odk.kalanso.stock.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private VendeurService vendeurService;
    @Autowired
    private EntrepotService entrepotService;

    @GetMapping("/all")
    public List<Admin> allAdmin(){
        return  adminService.allAdmin();
    }
    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    @PutMapping("/update/{id}")
    public Admin updateAdmin(@PathVariable Long id,
                             @RequestBody Admin admin) {
        return adminService.updateAdmin(id,admin);
    }
    //Manager
    @GetMapping("/manager/all")
    public List<Manager> allAManager(){
        return  managerService.allManager();
    }
    @PostMapping("manager/create")
    public Manager createManager(@RequestBody Manager manager){
        return managerService.createManager(manager);
    }

    @PutMapping("manager/update/{id}")
    public Manager updateManager(@PathVariable Long id,
                             @RequestBody Manager manager) {
        return managerService.updateManager(id,manager);
    }
    //delete Manager
    @DeleteMapping("manager/delete/{id}")
    public void deleteManager(@PathVariable long id){
         managerService.deleteManager(id);
    }
    //Role
    @GetMapping("/role/{id}")
    public Optional<Role> getRole(@PathVariable int id) {
        return roleService.getRoleById(id);
    }

    //Create Role
    @PostMapping("role/create/")
    public Role createRole( @RequestBody Role role) {
        return roleService.createRole(role);
    }

    //Modifier role
    @PutMapping("role/update/{id}/")
    public ResponseEntity<Role> updateRole(@PathVariable int id,
                                           @RequestBody Role roleDetails) {
        return ResponseEntity.ok(roleService.updateRole(id, roleDetails));
    }
    //delete
    @DeleteMapping("role/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    //Vendeur

    @GetMapping("/vendeur/all")
    public List<Vendeur> allVendeur(){
        return  vendeurService.allVendeurs();
    }

    //Creer Vendeur
    @PostMapping("/vendeur/create")
    public Vendeur createVendeur(@RequestBody Vendeur vendeur){
        return vendeurService.addVendeur(vendeur);
    }

    //updateVendeur
    @PutMapping("vendeur/update/{id}")
    public Vendeur updateVendeur(@PathVariable Long id, @RequestBody Vendeur vendeur) {

        return vendeurService.updateVendeur(id, vendeur);
    }

    //delete VENDEUR
    @DeleteMapping("/vendeur/delete/{id}")
    public void deleteVendeur(@PathVariable long id){
        managerService.deleteManager(id);
    }

    //Entrepot
    //creer Entrepot
    @PostMapping("/entrepot/create")
    public Entrepot createEntrepot(@RequestBody Entrepot entrepot){
        return entrepotService.createEntrepot(entrepot);
    }
    //get by Id
    @GetMapping("entrepot/id")
    public Optional<Entrepot> getEntrepotById(Long id){
        return entrepotService.getEntrepotById(id);
    }
    //List des ENtrepot
    @PostMapping("entrepot/list")
    public List<Entrepot> listEntrepot(@RequestBody Entrepot entrepotList){
        return entrepotService.getAllEntrepot();
    }
    //Modifier Entrepot
    @PostMapping("entrepot/update/{id}")
    public Entrepot updateEntrepot(@PathVariable int id, @RequestBody Entrepot entrepot) {
        return entrepotService.updateEntrepot(entrepot, id);
    }
    //suprimer Entrepot
    @DeleteMapping("entrepot/delete/{id}")
    public void deleteEntrepot(@PathVariable long id) {
        entrepotService.deleteEntrepot(id);
    }
}

