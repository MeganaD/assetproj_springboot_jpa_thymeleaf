package io.esls.asset.domain;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Asset {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Asset() {}

    @Builder
    public Asset(String name, String mngNo, AssetStatus status, String user, String purchasedDate,
            Integer purchasedPrice, String usage, String description) {
        this.name = name;
        this.mngNo = mngNo;
        this.status = status;
        this.user = user;
        this.purchasedDate = purchasedDate;
        this.purchasedPrice = purchasedPrice;
        this.usage = usage;
        this.description = description;
    }

    

}
