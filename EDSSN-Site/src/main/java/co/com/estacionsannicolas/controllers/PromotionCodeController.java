package co.com.estacionsannicolas.controllers;

import co.com.estacionsannicolas.beans.PageBean;
import co.com.estacionsannicolas.beans.PromotionCodeBean;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.repositories.specifications.PromotionCodeSpecification;
import co.com.estacionsannicolas.service.PromotionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotion-codes")
public class PromotionCodeController extends BaseController {

    @Autowired
    private PromotionCodeService promotionCodeService;

    @RequestMapping(method = RequestMethod.GET)
    public PageBean<PromotionCodeBean> getAll(Pageable pageRequest) {
        return promotionCodeService.getAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"page"})
    public PageBean<PromotionCodeBean> getAll(@RequestBody(required = false) PromotionCodeBean specification, Pageable pageRequest) {
        return promotionCodeService.getAll(new PromotionCodeSpecification(specification), pageRequest);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public PromotionCodeBean save(@RequestBody PromotionCodeBean promotionCodeBean) {
        return promotionCodeService.save(promotionCodeBean);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PromotionCodeBean update(@RequestBody PromotionCodeBean promotionCodeBean) {
        return promotionCodeService.save(promotionCodeBean);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        promotionCodeService.delete(id);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long count() {
        return promotionCodeService.getCount();
    }

}
