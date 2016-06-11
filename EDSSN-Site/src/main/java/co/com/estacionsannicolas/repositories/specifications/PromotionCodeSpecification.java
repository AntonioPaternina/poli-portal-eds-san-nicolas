package co.com.estacionsannicolas.repositories.specifications;

import co.com.estacionsannicolas.beans.PromotionCodeBean;
import co.com.estacionsannicolas.entities.PromotionCodeEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PromotionCodeSpecification implements Specification<PromotionCodeEntity> {

    private PromotionCodeBean criteria;

    public PromotionCodeSpecification(PromotionCodeBean criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<PromotionCodeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();
        if (criteria != null) {
            if (criteria.getCode() != null) {
                predicates.add(cb.like(root.get("code"), "%" + StringUtils.upperCase(criteria.getCode()) + "%"));
            }
            if (criteria.getUsed() != null) {
                predicates.add(cb.equal(root.get("used"), criteria.getUsed()));
            }
            if (criteria.getPoints() != null) {
                predicates.add(cb.equal(root.get("points"), criteria.getPoints()));
            }
            if (criteria.getMarketingCampaign() != null && criteria.getMarketingCampaign().getName() != null) {
                predicates.add(cb.like(root.get("marketingCampaign").get("name"), "%" + StringUtils.upperCase(criteria.getMarketingCampaign().getName()) + "%"));
            }
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
