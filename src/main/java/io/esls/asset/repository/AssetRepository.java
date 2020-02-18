package io.esls.asset.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import io.esls.asset.domain.Asset;
import io.esls.asset.domain.AssetStatus;
import io.esls.asset.web.form.AssetSearch;

/**
 * AssetRepository
 */


@Repository
public class AssetRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Asset asset) {
        if(asset.getId() == null) {
            em.persist(asset);
        }
        else {
            Asset nAsset = em.find(Asset.class, asset.getId());
            nAsset.setName(asset.getName());
            nAsset.setMngNo(asset.getMngNo());
            nAsset.setPurchasedDate(asset.getPurchasedDate());
            nAsset.setPurchasedPrice(asset.getPurchasedPrice());
            nAsset.setStatus(asset.getStatus());
            nAsset.setUsage(asset.getUsage());
            nAsset.setUser(asset.getUser());
        }
    }

    public void update(Long id, String name, String mngNo, AssetStatus status, String user, String purchasedDate,
            Integer purchasedPrice, String usage, String description) {
        Asset nAsset = em.find(Asset.class, id);
        nAsset.setName(name);
        nAsset.setMngNo(mngNo);
        nAsset.setPurchasedDate(purchasedDate);
        nAsset.setPurchasedPrice(purchasedPrice);
        nAsset.setStatus(status);
        nAsset.setUsage(usage);
        nAsset.setUser(user);
}
    
    public Asset findOne(Long id) {
        return em.find(Asset.class, id);
    }

    public List<Asset> findAll() {
        return em.createQuery("select a from Asset a", Asset.class)
            .getResultList();
    }

    public List<Asset> findAssets(AssetSearch assetSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Asset> cq = cb.createQuery(Asset.class);
        Root<Asset> a = cq.from(Asset.class);
        List<Predicate> criteria = new ArrayList<>();
    
        if (assetSearch.getAssetStatus() != null) {
            Predicate status = cb.equal(a.get("status"), assetSearch.getAssetStatus());
            criteria.add(status);
        }
        
        if (StringUtils.hasText(assetSearch.getAssetName())) {
            Predicate name = cb.like(a.<String>get("name"), "%" + assetSearch.getAssetName() + "%");
            criteria.add(name);
        }
        
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Asset> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }

}