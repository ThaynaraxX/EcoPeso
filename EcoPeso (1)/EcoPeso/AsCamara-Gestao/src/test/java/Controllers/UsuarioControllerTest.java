package Controllers;

import br.com.ascamaras.Controllers.UsuarioController;
import br.com.ascamaras.Dtos.UsuarioDto;
import br.com.ascamaras.Model.UsuarioModel;
import br.com.ascamaras.Repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void testCriarUsuario() throws Exception {
        UsuarioDto usuarioDTO = new UsuarioDto();
        usuarioDTO.setNome("João Silva");
        usuarioDTO.setEmail("joao@gmail.com");
        usuarioDTO.setSenha("abc123");

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome("João Silva");
        usuario.setEmail("joao@gmail.com");
        usuario.setSenha("abc123");

        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                        .contentType("application/json")
                        .content("{ \"nome\": \"João Silva\", \"email\": \"joao@gmail.com\", \"senha\": \"abc123\" }"))
                .andExpect(status().isCreated());
    }
}
