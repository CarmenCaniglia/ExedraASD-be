package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@JsonIgnoreProperties({"password"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String avatar;

    @OneToMany(mappedBy = "utente")
    private List<Abbonamento> abbonamenti;

    @OneToMany(mappedBy = "utente")
    private List<PrenotazioneCorso> prenotazioniCorsi;

    @OneToMany(mappedBy = "utente")
    private List<SchedaAllenamento> schedeAllenamento;

    @OneToMany(mappedBy = "utente")
    private List<SchedaNutrizionale> schedeNutrizionali;

    @OneToMany(mappedBy = "utente")
    private List<Ordine> ordini;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //lista dei ruoli utente
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
