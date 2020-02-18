package io.esls.asset.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.esls.asset.domain.Asset;
import io.esls.asset.service.AssetService;
import io.esls.asset.web.form.AssetForm;
import io.esls.asset.web.form.AssetSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AssetController
 */
@Controller
@RequestMapping("asset")
@Slf4j
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("")
    public String assetList(@ModelAttribute("assetSearch") AssetSearch assetSearch, Model model) {

        //List<Asset> assets = assetService.allAssets();
        List<Asset> assets = assetService.findAssets(assetSearch);
        model.addAttribute("assets", assets);

        return "asset/list";
    }

    @GetMapping("new")
    public String createAssetForm(Model model) {
        log.info("asset/new controller");
        model.addAttribute("assetForm", new AssetForm());

        return "asset/createForm";
    }

    @PostMapping(value = "new")
    public String createAsset(@Validated AssetForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "asset/createForm";
        }

        Asset asset = new Asset();

        asset.setName(form.getName());
        asset.setMngNo(form.getMngNo());
        asset.setPurchasedDate(form.getPurchasedDate());
        asset.setPurchasedPrice(form.getPurchasedPrice());
        asset.setStatus(form.getStatus());
        asset.setUsage(form.getUsage());
        asset.setUser(form.getUser());

        assetService.newAsset(asset);
        return "redirect:/asset";
    }

    @GetMapping(value = "/{assetId}")
    public String viewAsset(@PathVariable("assetId") Long assetId, Model model) {
        Asset asset = assetService.findOne(assetId);
        model.addAttribute("asset", asset);
        return "asset/view";
    }

    @GetMapping(value = "/{assetId}/edit")
    public String updateAssetForm(@PathVariable("assetId") Long assetId, Model model) {
        Asset asset = assetService.findOne(assetId);
        AssetForm assetForm = new AssetForm();

        assetForm.setName(asset.getName());
        assetForm.setMngNo(asset.getMngNo());
        assetForm.setPurchasedDate(asset.getPurchasedDate());
        assetForm.setPurchasedPrice(asset.getPurchasedPrice());
        assetForm.setStatus(asset.getStatus());
        assetForm.setUsage(asset.getUsage());
        assetForm.setUser(asset.getUser());

        model.addAttribute("assetForm", assetForm);
        return "asset/update";
    }

    @PostMapping(value = "/{assetId}/edit")
    public String updateAsset(@PathVariable("assetId") Long assetId, @ModelAttribute("form") AssetForm assetForm) {
        assetService.updateAsset(assetId, assetForm.getName(), assetForm.getMngNo(),
                assetForm.getStatus(), assetForm.getUser(), assetForm.getPurchasedDate(), assetForm.getPurchasedPrice(),
                assetForm.getUsage(), assetForm.getDescription());
        return "redirect:/asset";
    }
}