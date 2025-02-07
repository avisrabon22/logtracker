package com.iimi.logtracker.Controllers;
import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import com.iimi.logtracker.DTOs.UpdateRoleRequestDto;
import com.iimi.logtracker.Services.RoleInterface;
import com.iimi.logtracker.Services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
     private final RoleInterface roleInterface;

    public RoleController(RoleService roleInterface) {
        this.roleInterface = roleInterface;
    }

    @GetMapping("/get-roles")
    public ResponseEntity<?> getRoles() throws Exception {
        List<RoleResponseDto> roleResponseDtos = roleInterface.getRoles();
        return ResponseEntity.ok().body(roleResponseDtos);

    }

     @PostMapping("/add-role")
     public ResponseEntity<?> createRole(@RequestBody RoleRequestDto roleRequestDto) throws Exception {
        if (roleRequestDto.getRoleName().isEmpty()) {
            return ResponseEntity.badRequest().body("Role name cannot be empty");
        }
         RoleResponseDto roleResponseDto = roleInterface.createRole(roleRequestDto);
         return ResponseEntity.ok(roleResponseDto);
     }

     @GetMapping("/get-role/{id}")
     public ResponseEntity<?> getRole(@PathVariable Long id) throws Exception {
         RoleResponseDto roleResponseDto = roleInterface.getRole(id);
         return ResponseEntity.ok(roleResponseDto);
     }

     @PutMapping("/update-role")
     public ResponseEntity<?> updateRole(@RequestBody UpdateRoleRequestDto updateRoleRequestDto) throws Exception {
         RoleResponseDto roleResponseDto = roleInterface.updateRole(updateRoleRequestDto);
         return ResponseEntity.ok().body(roleResponseDto);
     }

     @DeleteMapping("/delete-role/{id}")
     public ResponseEntity<?> deleteRole(@PathVariable Long id) throws Exception {
         RoleResponseDto roleResponseDto= roleInterface.deleteRole(id);
         return ResponseEntity.ok().body(roleResponseDto);
     }

}
