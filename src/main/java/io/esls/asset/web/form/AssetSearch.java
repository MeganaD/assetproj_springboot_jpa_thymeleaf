package io.esls.asset.web.form;

import io.esls.asset.domain.AssetStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * AssetSearch
 */
@Getter @Setter
public class AssetSearch {
    private String assetName;
    private AssetStatus assetStatus;    
}