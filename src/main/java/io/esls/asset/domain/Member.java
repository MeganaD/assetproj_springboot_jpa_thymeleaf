package io.esls.asset.domain;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Member {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
    private Long id;
    
    private String email;
    private String name;

    protected Member() {}

    @Builder
    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    

    
    
}