package Services;

import br.com.ascamaras.Dtos.UsuarioDto;
import br.com.ascamaras.Model.UsuarioModel;
import br.com.ascamaras.Repositories.UsuarioRepository;
import br.com.ascamaras.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarUsuario_Sucesso() {
        UsuarioDto usuarioDTO = new UsuarioDto();
        usuarioDTO.setNome("João Silva");
        usuarioDTO.setEmail("joao@gmail.com");
        usuarioDTO.setSenha("abc123");

        // Supondo que o método save do repositório retorna o mesmo usuário
        UsuarioModel usuarioEsperado = new UsuarioModel();
        usuarioEsperado.setNome("João Silva");
        usuarioEsperado.setEmail("joao@gmail.com");
        usuarioEsperado.setSenha("abc123");

        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuarioEsperado);

        UsuarioModel usuario = usuarioService.criarUsuario(usuarioDTO);

        assertNotNull(usuario);
        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@gmail.com", usuario.getEmail());
    }

    @Test
    public void testValidarSenha_Valida() {
        assertTrue(usuarioService.validarSenha("ab"));
    }

    @Test
    public void testValidarSenha_Invalida() {
        assertFalse(usuarioService.validarSenha("aa"));
    }

    @Test
    public void testValidarEmail_Valido() {
        assertTrue(usuarioService.validarEmail("joao@gmail.com"));
    }

    @Test
    public void testValidarEmail_Invalido() {
        assertFalse(usuarioService.validarEmail("joao@hotmail.com"));
    }
}
