package com.javakc.cms.service;

import com.javakc.cms.dao.CategoryDao;
import com.javakc.cms.entity.Category;
import com.javakc.cms.vo.category.FirstCategory;
import com.javakc.cms.vo.category.SecondCategory;
import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类 逻辑层
 */
@Service
public class CategoryService extends BaseService<CategoryDao, Category> {

    public List<FirstCategory> getCategoryList(){
//        查询一级分类
        SimpleSpecificationBuilder simpleSpecificationBuilder1 = new SimpleSpecificationBuilder();
        simpleSpecificationBuilder1.and("parentId","=","0");
        Specification<Category> specification1 = simpleSpecificationBuilder1.getSpecification();
        List<Category> categories1 = dao.findAll(specification1);
//        查询二级分类
        SimpleSpecificationBuilder simpleSpecificationBuilder2= new SimpleSpecificationBuilder();
        simpleSpecificationBuilder2.and("parentId","!=","0");
        Specification<Category> specification2 = simpleSpecificationBuilder2.getSpecification();
        List<Category> categories2 = dao.findAll(specification2);

        List<FirstCategory> firstCategoryList=new ArrayList<>();
        for (int i = 0; i < categories1.size(); i++) {
//            得到集合里的每个查询出来的对象
            Category category1 = categories1.get(i);
//            得到一个空的FirstCategory 对象用来赋值
            FirstCategory firstCategory=new FirstCategory();
//            使用工具类 方法复制
            BeanUtils.copyProperties(category1,firstCategory);

            for (int j = 0; j < categories2.size(); j++) {
                if (categories2.get(j).getParentId().equals(category1.getId())){
                    SecondCategory secondCategory=new SecondCategory();
                    BeanUtils.copyProperties(categories2.get(j),secondCategory);
                    firstCategory.getSecondCategoryList().add(secondCategory);
                }

            }
            firstCategoryList.add(firstCategory);

        }


        return firstCategoryList;
    }
}
