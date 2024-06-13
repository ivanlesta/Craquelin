package udaw2.proyecto.ilcProy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.repositories.ClienteRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null)
            throw (new UsernameNotFoundException("Cliente no encontrado!"));
        return User
                .withUsername(email)
                .roles(cliente.getRol().toString())
                .password(cliente.getContraseña())
                .build();
    }
}
