package io.esls.asset.web.form;

import javax.validation.constraints.NotEmpty;

import io.esls.asset.domain.AssetStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * AssetForm
 */
@Getter @Setter
public class AssetForm {

    @NotEmpty(message = "필수")
    private String name;
    
    private String mngNo;
    private AssetStatus status;
    private String user;
    private String purchasedDate;
    private Integer purchasedPrice;
    private String usage;
    private String description;
    
    
}