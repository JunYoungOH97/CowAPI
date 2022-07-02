package toyspringboot.server.Domain.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class User implements UserDetails {
    @Id
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private Boolean admin;

    @Column
    @NotNull
    private Boolean isDeleted;

    @Column
    @NotNull
    private Timestamp createdDate;

    @Column
    @NotNull
    private Timestamp updatedDate;

    @Column
    private Timestamp deletedDate;

    @Column
    @NotNull
    private String creator;

    @Column
    private String updater;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<QnA> QnAs = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Notice> Notices = new ArrayList<>();


    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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
