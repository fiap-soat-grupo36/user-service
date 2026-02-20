package br.com.fiap.oficina.user_service.dto.response;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String username;
    private String nome;
    private String role;
}
