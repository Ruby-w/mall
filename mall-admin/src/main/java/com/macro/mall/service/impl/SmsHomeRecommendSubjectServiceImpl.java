package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsHomeRecommendSubjectMapper;
import com.macro.mall.model.SmsHomeRecommendSubject;
import com.macro.mall.model.SmsHomeRecommendSubjectExample;
import com.macro.mall.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendSubjectServiceImpl
 * @Description 首页专题推荐管理Service实现类
 * @Author AW
 * @Date 2020/8/20 002023:42
 * @Version V1.0
 **/
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Autowired
    private SmsHomeRecommendSubjectMapper subjectMapper;

    @Override
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        for (SmsHomeRecommendSubject subject : recommendSubjectList) {
            subject.setRecommendStatus(1);
            subject.setSort(0);
            subjectMapper.insert(subject);
        }
        return recommendSubjectList.size();

    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setId(id);
        subject.setSort(sort);
        return subjectMapper.updateByPrimaryKeySelective(subject);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.createCriteria().andIdIn(ids);
        return subjectMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setRecommendStatus(recommendStatus);
        return subjectMapper.updateByExampleSelective(subject, example);
    }

    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        if (!StringUtils.isEmpty(subjectName)) {
            example.createCriteria().andSubjectNameLike("%" + subjectName + "%");
        }
        if (recommendStatus != 0) {
            example.createCriteria().andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return subjectMapper.selectByExample(example);
    }
}
