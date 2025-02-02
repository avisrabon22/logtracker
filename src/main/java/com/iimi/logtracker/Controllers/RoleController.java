package com.iimi.logtracker.Controllers;

import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import com.iimi.logtracker.Services.RoleService;
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

     @PostMapping("/add-role")
     public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto) throws Exception {
         RoleResponseDto roleResponseDto = roleService.createRole(roleRequestDto);
         return ResponseEntity.ok(roleResponseDto);
     }

     @GetMapping("/get-roles")
     public ResponseEntity<List<RoleResponseDto>> getRoles() throws Exception {
         List<RoleResponseDto> roleResponseDtos = roleService.getRoles();
         return ResponseEntity.ok(roleResponseDtos);
     }

     @GetMapping("/get-role/{roleName}")
     public ResponseEntity<RoleResponseDto> getRole(@PathVariable String roleName) throws Exception {
         RoleResponseDto roleResponseDto = roleService.getRole(roleName);
         return ResponseEntity.ok(roleResponseDto);
     }

     @PutMapping("/update-role/{roleName}")
     public ResponseEntity<RoleResponseDto> updateRole(@PathVariable String roleName, @RequestBody RoleRequestDto roleRequestDto) throws Exception {
         RoleResponseDto roleResponseDto = roleService.updateRole(roleRequestDto);
         return ResponseEntity.ok(roleResponseDto);
     }

     @DeleteMapping("/delete-role/{roleName}")
     public ResponseEntity<?> deleteRole(@PathVariable String roleName) throws Exception {
         RoleResponseDto roleResponseDto=roleService.deleteRole(roleName);
         return ResponseEntity.ok(roleResponseDto);
     }

}
