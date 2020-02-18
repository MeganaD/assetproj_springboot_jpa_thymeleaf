package io.esls.asset.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter 
@NoArgsConstructor
public class Asset {

    @Id @GeneratedValue
	@Column(name="asset_id")
	private Long id;
    
    private String name;
    private String mngNo;
    
    @Enumerated(EnumType.STRING)
	private AssetStatus status;
    private String user;
    private String purchasedDate;
    private Integer purchasedPrice;
    private String usage;
    private String description;

}
