package com.iimi.logtracker.Controllers;
import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import com.iimi.logtracker.Services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
     private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get-roles")
    public ResponseEntity<?> getRoles() throws Exception {
        List<RoleResponseDto> roleResponseDtos = roleService.getRoles();
        return ResponseEntity.ok().body(roleResponseDtos);

    }

     @PostMapping("/add-role")
     public ResponseEntity<?> createRole(@RequestBody RoleRequestDto roleRequestDto) throws Exception {
         RoleResponseDto roleResponseDto = roleService.createRole(roleRequestDto);
         return ResponseEntity.ok(roleResponseDto);
     }

     @GetMapping("/get-role/{id}")
     public ResponseEntity<?> getRole(@PathVariable Long id) throws Exception {
         RoleResponseDto roleResponseDto = roleService.getRole(id);
         return ResponseEntity.ok(roleResponseDto);
     }

     @PutMapping("/update-role")
     public ResponseEntity<?> updateRole(@RequestBody RoleRequestDto roleRequestDto) throws Exception {
         RoleResponseDto roleResponseDto = roleService.updateRole(roleRequestDto);
         return ResponseEntity.ok(roleResponseDto);
     }

     @DeleteMapping("/delete-role/{id}")
     public ResponseEntity<?> deleteRole(@PathVariable Long id) throws Exception {
         RoleResponseDto roleResponseDto=roleService.deleteRole(id);
         return ResponseEntity.ok(roleResponseDto);
     }

}
