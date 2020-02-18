package io.esls.asset.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.esls.asset.domain.Asset;
import io.esls.asset.domain.AssetStatus;
import io.esls.asset.repository.AssetRepository;
import io.esls.asset.web.form.AssetSearch;
import lombok.RequiredArgsConstructor;

/**
 * AssetService
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    @Transactional
    public Long newAsset(Asset asset) {
        assetRepository.save(asset);
        return asset.getId();
    }

    @Transactional
    public Asset updateAsset(Long id, String name, String mngNo, AssetStatus status, String user, String purchasedDate,
            Integer purchasedPrice, String usage, String description) {
        assetRepository.update(id, name, mngNo, status, user, purchasedDate, purchasedPrice, usage, description);
        return assetRepository.findOne(id);

    }

    public Asset findOne(Long assetId) {
        return assetRepository.findOne(assetId);
    }

    public List<Asset> findAssets(AssetSearch assetSearch) {
        // return assetRepository.findAll();
        return assetRepository.findAssets(assetSearch);
    }

    public List<Asset> allAssets() {
        return assetRepository.findAll();
    }

}