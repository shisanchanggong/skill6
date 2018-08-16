package cn.skill6.website.dao;

import cn.skill6.common.entity.po.CategoryInfo;
import java.util.List;

/**
 * 目录分类信息mapper
 *
 * @author 何明胜
 * @created at 2018年8月15日 下午11:39:53
 * @version 1.0.0
 */
public interface CategoryInfoMapper {
  int deleteByPrimaryKey(Long categoryId);

  int insert(CategoryInfo record);

  CategoryInfo selectByPrimaryKey(Long categoryId);

  List<CategoryInfo> selectAll();

  int updateByPrimaryKey(CategoryInfo record);
}
